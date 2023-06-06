package com.mis.altclinic.controllers;

import com.mis.altclinic.users.authentication.AuthenticationDto;
import com.mis.altclinic.users.authentication.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserAuthenticationService userAuthenticationService;

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("authentication",new AuthenticationDto());
        return "login";
    }

    @PostMapping
    public String login(Model model, @RequestParam String email, @RequestParam String password) {
        userAuthenticationService.authenticate(email, password);
        return "redirect:/";
    }
}
