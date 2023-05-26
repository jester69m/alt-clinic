package com.mis.altclinic.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@NoArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
