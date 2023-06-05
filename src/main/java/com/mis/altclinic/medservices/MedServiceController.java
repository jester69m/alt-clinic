package com.mis.altclinic.medservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medservices")
@RequiredArgsConstructor
public class MedServiceController {

    private final MedServiceRepository medServiceRepository;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("medServices", medServiceRepository.findAll());
        return "medservices/list";
    }

    @GetMapping("/add")
     public String showAddForm(Model model) {
        model.addAttribute("medService", new MedService());
        return "medservices/add";
    }
    @PostMapping("/add")
    public String addMedService(@ModelAttribute("medService") MedService medService, RedirectAttributes redirectAttributes) {
        medServiceRepository.save(medService);
        return "redirect:/medservices";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, Long id) {
        model.addAttribute("medService", medServiceRepository.findById(id));
        return "medservices/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMedService(@ModelAttribute("medService") MedService medService, RedirectAttributes redirectAttributes) {
        medServiceRepository.save(medService);
        return "redirect:/medservices";
    }
}
