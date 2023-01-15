package com.example.pwpolicy.controllers;

import com.example.pwpolicy.dto.ChangePassword;
import com.example.pwpolicy.services.PolicyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("public")
public class AppController {

    private PolicyService policyService;

    public AppController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("")
    public String index(Model model, Principal principal) {
        if (this.policyService.showPasswordWarning(principal.getName())) {
            model.addAttribute("showPasswordWarning", 10);
        }
        return "index";
    }

    @GetMapping("change-password")
    public String changePassword() {
        return "change-password";
    }

    @PostMapping("change-password")
    public String updatePassword(@RequestBody ChangePassword password, Model model) {
        model.addAttribute("errors", this.policyService.validatePassword(password));
        return "change-password";
    }

}
