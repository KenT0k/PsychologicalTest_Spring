package com.interlink.psychological_tests.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(ModelMap modelMap, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            modelMap.put("error", "Invalid username and/or password.");
        }
        return "authentication/login.html";
    }
}