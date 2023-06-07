package com.mis.altclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password) {
        return "redirect:/";
    }
}
