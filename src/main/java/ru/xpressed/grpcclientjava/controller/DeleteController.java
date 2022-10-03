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

@Controller
public class DeleteController {
    private SecurityConfiguration securityConfiguration;

    @Autowired
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @GetMapping("/delete")
    public String showUpdateForm(HttpServletRequest request, Model model) {
        model.addAttribute("welcome", "Delete \"" + request.getUserPrincipal().getName() + "\"?");
        model.addAttribute("user", new User());
        return "delete";
    }

    @PostMapping("/delete")
    public String completeUpdate(User user, Model model, HttpServletRequest request) {
        model.addAttribute("welcome", "Delete \"" + request.getUserPrincipal().getName() + "\"?");

        if (!securityConfiguration.encoder().matches(user.getOld(), UserRequest.get(request.getUserPrincipal().getName())[1])) {
            model.addAttribute("olderr", "Old password is incorrect!");
            return "delete";
        } else {
            UserRequest.delete(request.getUserPrincipal().getName());
            model.addAttribute("message", "Deletion Completed");
            model.addAttribute("onload", "redirectTimer()");
        }

        return "delete";
    }
}
