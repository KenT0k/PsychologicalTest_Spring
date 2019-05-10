package com.interlink.psychological_tests.tests.controller;

import com.interlink.psychological_tests.tests.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/{titleOfTest}", method = RequestMethod.GET)
    public String getFirstTest(@PathVariable String titleOfTest, ModelMap modelMap) {
        modelMap.put("contentOfTest", testService.getTest(titleOfTest));
        modelMap.put("titleOfTest", titleOfTest);
        return "pageOfTest/test.html";
    }

    @RequestMapping(value = "/saveResult/title={titleOfTest}/result={resultForTest}", method = RequestMethod.GET)
    public String saveResult(@PathVariable String titleOfTest, @PathVariable int resultForTest) {
        testService.saveResult(titleOfTest, resultForTest);
        return "redirect:/{titleOfTest}";
    }
}