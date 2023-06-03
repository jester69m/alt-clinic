package com.mis.altclinic.medservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MedServiceController {

    private final MedServiceRepository medServiceRepository;

    public String index(Model model) {
        model.addAttribute("medServices", medServiceRepository.findAll());
        return "index";
    }
}
