package com.interlink.psychological_tests.controller;

import com.interlink.psychological_tests.authentication.user.User;
import com.interlink.psychological_tests.tests.dto.Test;
import com.interlink.psychological_tests.tests.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Controllers {
    private final TestService testService;

    @Autowired
    public Controllers(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.put("username", user.getUsername());
        modelMap.put("firstName", user.getFirstName());
        modelMap.put("role", user.getRole());
        modelMap.put("tests", testService.getCreatedTests());
        return "index.html";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String getErrorPage() {
        return "main/accessDenied.html";
    }
}