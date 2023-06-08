package com.mis.altclinic.consumers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("consumers", consumerService.findAll());
        model.addAttribute("consumer", new Consumer());
        return "consumers/list";
    }

    @GetMapping("/add")
    public String addConsumer(Model model) {
        model.addAttribute("consumer", new Consumer());
        return "consumers/add";
    }

    @PostMapping
    public String addConsumer(Consumer consumer, RedirectAttributes redirectAttributes) {
            consumerService.save(consumer);
        return "redirect:/list";
    }

}
