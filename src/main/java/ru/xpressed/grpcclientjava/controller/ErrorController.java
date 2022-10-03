package ru.xpressed.grpcclientjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public String showErrorPage() {
        return "error";
    }
}
