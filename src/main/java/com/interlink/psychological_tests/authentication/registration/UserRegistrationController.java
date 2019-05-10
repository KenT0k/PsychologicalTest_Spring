package com.interlink.psychological_tests.authentication.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping
    public String getRegistrationForm(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "authentication/registration.html";
    }

    @PostMapping
    public String register(UserRegistrationForm userRegistrationForm, Model model) {
        UserRegistrationResult userRegistrationResult = userRegistrationService.register(userRegistrationForm);
        if (userRegistrationResult.hasErrors()) {
            model.addAttribute("userRegistrationForm", userRegistrationForm);
            model.addAttribute("userRegistrationResult", userRegistrationResult);
            return "authentication/registration.html";
        } else {
            return "redirect:/login";
        }
    }
}