package com.mis.altclinic.consumers;

import com.mis.altclinic.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAll(Model model) {
        model.addAttribute("consumers", consumerService.findAll());
        model.addAttribute("consumer", new Consumer());
        return "consumers/list";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/info")
    public String showCustomerInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Consumer consumer = (Consumer) authentication.getPrincipal();

        long consumerId = consumer.getId();

        model.addAttribute("consumer", consumerService.getConsumerById(consumerId).orElseThrow());
        model.addAttribute("passwordEncoder", passwordEncoder);
        return "consumers/account";
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
