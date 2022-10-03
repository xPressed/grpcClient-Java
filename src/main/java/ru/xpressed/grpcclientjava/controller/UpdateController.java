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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UpdateController {
    @Autowired
    SecurityConfiguration securityConfiguration;

    @GetMapping("/update")
    public String showUpdateForm(HttpServletRequest request, Model model) {
        model.addAttribute("welcome", "Update \"" + request.getUserPrincipal().getName() + "\"?");
        model.addAttribute("user", new User());
        return "update";
    }

    @PostMapping("/update")
    public String completeUpdate(@Valid User user, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("welcome", "Update \"" + request.getUserPrincipal().getName() + "\"?");

        if (!securityConfiguration.encoder().matches(user.getOld(), UserRequest.get(request.getUserPrincipal().getName())[1])) {
            model.addAttribute("olderr", "Old password is incorrect!");
            return "update";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "update";
        }

        if (!user.getPassword().equals(user.getRepeated())) {
            model.addAttribute("reperr", "Passwords don't match!");
            return "update";
        }

        if (UserRequest.add(user.getUsername(), securityConfiguration.encoder().encode(user.getPassword()))) {
            UserRequest.delete(request.getUserPrincipal().getName());
            model.addAttribute("message", "Update Completed");
            model.addAttribute("onload", "redirectTimer()");
        } else {
            bindingResult.rejectValue("username", "user.username", "This username is already taken!");
        }

        return "update";
    }
}
