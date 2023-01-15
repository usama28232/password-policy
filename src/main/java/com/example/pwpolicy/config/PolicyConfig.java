package com.example.pwpolicy.config;

import com.example.pwpolicy.controllers.PolicyError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@PropertySource("app.password-policy")
@Configuration
public class PolicyConfig {

    @Value("${app.password-policy.min-special-chars:0}")
    private int minSpecialChars;
    @Value("${app.password-policy.min-numbers:0}")
    private int minNumbers;
    @Value("${app.password-policy.min-capital-letters:0}")
    private int minCapitalLetters;
    @Value("${app.password-policy.password-expiry-days:0}")
    private int passwordExpiryDays;
    @Value("${app.password-policy.show-warning-in-days:0}")
    private int showWarningInDays;
    @Value("${app.password-policy.min-length:0}")
    private int minLength;
    @Value("${app.password-policy.allowed-special-chars:''}")
    private String allowedSpecialChars;

    public int getMinSpecialChars() {
        return minSpecialChars;
    }

    public void setMinSpecialChars(int minSpecialChars) {
        this.minSpecialChars = minSpecialChars;
    }

    public int getMinNumbers() {
        return minNumbers;
    }

    public void setMinNumbers(int minNumbers) {
        this.minNumbers = minNumbers;
    }

    public int getMinCapitalLetters() {
        return minCapitalLetters;
    }

    public void setMinCapitalLetters(int minCapitalLetters) {
        this.minCapitalLetters = minCapitalLetters;
    }

    public int getPasswordExpiryDays() {
        return passwordExpiryDays;
    }

    public void setPasswordExpiryDays(int passwordExpiryDays) {
        this.passwordExpiryDays = passwordExpiryDays;
    }

    public int getShowWarningInDays() {
        return showWarningInDays;
    }

    public void setShowWarningInDays(int showWarningInDays) {
        this.showWarningInDays = showWarningInDays;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public String getAllowedSpecialChars() {
        return allowedSpecialChars;
    }

    public void setAllowedSpecialChars(String allowedSpecialChars) {
        this.allowedSpecialChars = allowedSpecialChars;
    }

    public List<PolicyError> getPolicyList() {
        List<PolicyError> policyErrorList = new ArrayList<>();
        if (minSpecialChars > 0) {
            policyErrorList.add(new PolicyError(
                    "", this.minSpecialChars

            ));
        }
        if (minNumbers > 0) {
            policyErrorList.add(new PolicyError(
                    "", this.minNumbers

            ));
        }
        if (minCapitalLetters > 0) {
            policyErrorList.add(new PolicyError(
                    "", this.minCapitalLetters

            ));
        }
        return policyErrorList;
    }

}
