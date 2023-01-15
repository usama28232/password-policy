package com.example.pwpolicy.listeners;

import com.example.pwpolicy.services.PolicyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationSuccessHandler implements AuthenticationSuccessHandler {

    private PolicyService policyService;

    public ApplicationSuccessHandler(PolicyService policyService) {
        this.policyService = policyService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, RuntimeException {
        /*  It is logically better to trigger/generate password warning from event handler instead of a controller because an application can have multiple entry points  */
//        this.policyService.showWarningInDays();
        this.policyService.isPasswordExpired(authentication.getName(), request.getLocale());
    }
}
