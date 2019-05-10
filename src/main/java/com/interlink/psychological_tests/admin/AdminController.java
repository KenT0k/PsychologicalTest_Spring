package com.interlink.psychological_tests.admin;

import com.interlink.psychological_tests.tests.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final TestService testService;

    @Autowired
    public AdminController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/getAllResult", method = RequestMethod.GET)
    public String getAllResultPage(ModelMap modelMap) {
        modelMap.put("allFinishedTest", testService.getResultFromAllUsers());
        return "admin/allResult.html";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAddTestsPage(ModelMap modelMap) {
        modelMap.put("createdTests", testService.getCreatedTests());
        return "admin/admin.html";
    }

    @RequestMapping(value = "/admin/addTest", method = RequestMethod.POST)
    public String saveTest(@RequestParam String titleOfTest) {
        testService.addTest(titleOfTest);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/titleOfTest={titleOfTest}", method = RequestMethod.GET)
    public String removeTest(@PathVariable String titleOfTest) {
        testService.removeTest(titleOfTest);
        return "redirect:/admin";
    }
}