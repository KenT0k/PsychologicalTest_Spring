package com.interlink.psychological_tests.edit;

import com.interlink.psychological_tests.authentication.registration.UserRegistrationForm;
import com.interlink.psychological_tests.authentication.registration.UserRegistrationService;
import com.interlink.psychological_tests.authentication.user.User;
import com.interlink.psychological_tests.authentication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit")
public class EditUserController {
    private final UserService userService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public EditUserController(UserService userService, UserRegistrationService userRegistrationService) {
        this.userService = userService;
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping
    public String getEditForm(ModelMap modelMap, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.put("username", user.getUsername());
        modelMap.put("firstName", user.getFirstName());
        modelMap.put("lastName", user.getLastName());
        modelMap.put("group", user.getGroup());
        modelMap.put("email", user.getEmail());
        model.addAttribute("user", new UserRegistrationForm());
        return "main/editUser.html";
    }

    @PostMapping
    public String changeDataToUser(UserRegistrationForm userRegistrationForm, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateUser(userRegistrationService.insertDataToUser(userRegistrationForm), user.getUsername());
        model.addAttribute("user", new UserRegistrationForm());
        return "redirect:/";
    }
}