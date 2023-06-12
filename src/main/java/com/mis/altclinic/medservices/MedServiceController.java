package com.mis.altclinic.medservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medservices")
@RequiredArgsConstructor
public class MedServiceController {

    private final MedServiceService medServiceService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("medServices", medServiceService.findAll());
        return "medservices/list";
    }

    @GetMapping("/add")
     public String showAddForm(Model model) {
        model.addAttribute("medService", new MedService());
        return "medservices/add";
    }
    @PostMapping("/add")
    public String addMedService(@ModelAttribute("medService") MedService medService) {
        medServiceService.save(medService);
        return "redirect:/medservices";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("medService", medServiceService.findById(id).orElseThrow());
        return "medservices/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMedService(@ModelAttribute("medService") MedService medService) {
        medServiceService.save(medService);
        return "redirect:/medservices";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedService(@PathVariable Long id) {
        medServiceService.deleteById(id);
        return "redirect:/medservices";
    }
}
