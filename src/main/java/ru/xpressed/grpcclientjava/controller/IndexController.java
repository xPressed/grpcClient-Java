package ru.xpressed.grpcclientjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping({"/index", "/", "/home"})
    public String showIndexPage(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", "Welcome, " + request.getUserPrincipal().getName() + "!");

            model.addAttribute("loginOrUpdate", "/update");
            model.addAttribute("textLoginOrUpdate", "Update");

            model.addAttribute("registerOrLogout", "/logout");
            model.addAttribute("textRegisterOrLogout", "LogOut");

            model.addAttribute("del", true);
        }
        else {
            model.addAttribute("username", "Welcome, Stranger?");

            model.addAttribute("loginOrUpdate", "/login");
            model.addAttribute("textLoginOrUpdate", "LogIn");

            model.addAttribute("registerOrLogout", "/registration");
            model.addAttribute("textRegisterOrLogout", "Registration");

            model.addAttribute("del", false);
        }
        return "index";
    }
}
