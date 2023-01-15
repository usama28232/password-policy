package com.example.pwpolicy.services;

import com.example.pwpolicy.config.PolicyConfig;
import com.example.pwpolicy.controllers.PolicyError;
import com.example.pwpolicy.dto.ChangePassword;
import com.example.pwpolicy.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PolicyService {

    public static final String PW_EXPIRED_MSG = "app.password.expired";

    public static final String MIN_LEN_MSG = "app.password-policy.messages.password-min-length";
    public static final String MIN_SPC_MSG = "app.password-policy.messages.password-min-special-chars";
    public static final String MIN_NUM_MSG = "app.password-policy.messages.password-min-numbers";
    public static final String MIN_CAP_MSG = "app.password-policy.messages.password-min-capital-letters";

    public static final String NUM_REGEX = "[0-9]+";
    public static final String CAP_REGEX = "[A-Z]+";

    private PolicyConfig policyConfig;
    private UserService userService;

    private MessageSource messageSource;

    public PolicyService(PolicyConfig policyConfig, UserService userService, MessageSource messageSource) {
        this.policyConfig = policyConfig;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    private List<PolicyError> getAvailablePolicyErrors() {
        return this.policyConfig.getPolicyList();
    }

    public int getPasswordExpiryDays() {
        return this.policyConfig.getPasswordExpiryDays();
    }

    public int showWarningInDays() {
        return this.policyConfig.getShowWarningInDays();
    }

    public int getMinPasswordLength() {
        return this.policyConfig.getMinLength();
    }

    public void isPasswordExpired(String username, Locale locale) throws RuntimeException {
        User user = (User) this.userService.loadUserByUsername(username);
        if (this.getPasswordExpiryDays() > 0 && !user.isPasswordNeverExpire()) {
            long duration = System.currentTimeMillis() - user.getLastPasswordChangeDTM().getTime();
            if (TimeUnit.MILLISECONDS.toDays(duration) >= this.policyConfig.getPasswordExpiryDays()) {
                throw new RuntimeException(messageSource.getMessage(PW_EXPIRED_MSG, null, locale));
            }
        }
    }

    public boolean showPasswordWarning(String username) {
        if (this.showWarningInDays() > 0) {
            User user = (User) this.userService.loadUserByUsername(username);
            long duration = System.currentTimeMillis() - user.getLastPasswordChangeDTM().getTime();
            if (TimeUnit.MILLISECONDS.toDays(duration) <= this.policyConfig.getShowWarningInDays()) {
                return true;
            }
        }
        return false;
    }

    public boolean prePolicyValidation() {
        // compare old + new password
        // compare new & re-type password
        // .. check last password history from N records etc. suite your business need
        return true;
    }

    public List<PolicyError> validatePassword(ChangePassword password) {
        List<PolicyError> policyErrorList = new ArrayList<>();
        if (prePolicyValidation()) {
            this.hasMinLength(policyErrorList, password.getNewPassword())
                    .hasMinSpecialChars(policyErrorList, password.getNewPassword())
                    .hasMinNumbers(policyErrorList, password.getNewPassword())
                    .hasMinCapLetters(policyErrorList, password.getNewPassword());

        }
        return policyErrorList;
    }

    private PolicyService hasMinLength(List<PolicyError> policyErrorList, String password) {
        PolicyError policyError = null;
        if (this.policyConfig.getMinLength() > 0 && password.length() < this.getMinPasswordLength()) {
            policyError = new PolicyError(
                    MIN_LEN_MSG,
                    this.policyConfig.getMinLength()
            );
            policyErrorList.add(policyError);
        }
        return this;
    }

    private PolicyService hasMinSpecialChars(List<PolicyError> policyErrorList, String password) {
        PolicyError policyError = null;
        if (this.policyConfig.getMinSpecialChars() > 0) {
            int count = this.countByPattern(password, "[".concat(this.policyConfig.getAllowedSpecialChars()).concat("]+"));
            if (count < this.policyConfig.getMinSpecialChars()) {
                policyError = new PolicyError(
                        MIN_SPC_MSG,
                        this.policyConfig.getMinSpecialChars()
                );
                policyErrorList.add(policyError);
            }
        }
        return this;
    }

    private PolicyService hasMinCapLetters(List<PolicyError> policyErrorList, String password) {
        PolicyError policyError = null;
        if (this.policyConfig.getMinCapitalLetters() > 0) {
            int count = this.countByPattern(password, CAP_REGEX);
            if (count < this.policyConfig.getMinCapitalLetters()) {
                policyError = new PolicyError(
                        MIN_CAP_MSG,
                        this.policyConfig.getMinCapitalLetters()
                );
                policyErrorList.add(policyError);
            }
        }
        return this;
    }

    private PolicyService hasMinNumbers(List<PolicyError> policyErrorList, String password) {
        PolicyError policyError = null;
        if (this.policyConfig.getMinNumbers() > 0) {
            int count = this.countByPattern(password, NUM_REGEX);
            if (count < this.policyConfig.getMinNumbers()) {
                policyError = new PolicyError(
                        MIN_NUM_MSG,
                        this.policyConfig.getMinNumbers()
                );
                policyErrorList.add(policyError);
            }
        }
        return this;
    }

    private int countByPattern(String candidate, String pattern) {
        int count = 0;
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(candidate);
        while (matcher.find()) {
            count += matcher.group(0).length();
        }
        return count;
    }

}
