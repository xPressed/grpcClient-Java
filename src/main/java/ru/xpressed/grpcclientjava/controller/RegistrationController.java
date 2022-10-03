package ru.xpressed.grpcclientjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.xpressed.grpcclientjava.entity.User;
import ru.xpressed.grpcclientjava.security.SecurityConfiguration;
import ru.xpressed.grpcclientjava.server.UserRequest;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    SecurityConfiguration securityConfiguration;

    @GetMapping("/registration")
    public String showRegistrationForm(User user, Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String completeRegistration(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }

        if (!user.getPassword().equals(user.getRepeated())) {
            model.addAttribute("reperr", "Passwords don't match!");
            return "registration";
        }

        if (UserRequest.add(user.getUsername(), securityConfiguration.encoder().encode(user.getPassword()))) {
            model.addAttribute("message", "Registration Completed");
            model.addAttribute("onload", "redirectTimer()");
        } else {
            bindingResult.rejectValue("username", "user.username", "This username is already taken!");
        }

        return "registration";
    }
}
