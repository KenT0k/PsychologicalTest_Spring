package com.interlink.psychological_tests.account;

import com.interlink.psychological_tests.tests.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAccountController {
    private final TestService testService;

    @Autowired
    public UserAccountController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccountPage(ModelMap modelMap) {
        modelMap.put("finishedTest", testService.getResultFromUser());
        return "main/account.html";
    }

    @RequestMapping(value = "/deleteResult/idResult={idResult}", method = RequestMethod.GET)
    public String saveResult(@PathVariable int idResult) {
        testService.deleteResultFromUser(idResult);
        return "redirect:/account";
    }
}