package com.interlink.psychological_tests.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ExceptionHandlerController implements ErrorController {
    private final ExceptionHandlerService exceptionHandlerService;

    @Autowired
    public ExceptionHandlerController(ExceptionHandlerService exceptionHandlerService) {
        this.exceptionHandlerService = exceptionHandlerService;
    }

    @GetMapping
    public String handleError(HttpServletRequest request) {
        return exceptionHandlerService.differentStatusCode(request);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}