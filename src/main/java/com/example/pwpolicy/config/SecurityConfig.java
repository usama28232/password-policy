package com.example.pwpolicy.config;

import com.example.pwpolicy.listeners.ApplicationSuccessHandler;
import com.example.pwpolicy.services.PolicyService;
import com.example.pwpolicy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private PolicyService policyService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/public", true)
                .successHandler(applicationSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public ApplicationSuccessHandler applicationSuccessHandler() {
        return new ApplicationSuccessHandler(policyService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userService);
    }

}
