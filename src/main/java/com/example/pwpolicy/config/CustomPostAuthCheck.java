package com.example.pwpolicy.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

@Component
public class CustomPostAuthCheck implements UserDetailsChecker {

    @Override
    public void check(UserDetails toCheck) {
        System.out.println(toCheck);

    }
}
