package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final MedServiceRepository medService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        return "doctors/list";
    }

    @GetMapping("/add")
    public String addDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        model.addAttribute("medServices", medService.findAll());
        return "doctors/add";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute @RequestBody DoctorDto doctor) {
        doctorService.save(doctor);
        return "redirect:/doctors   ";
    }

    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.findById(id).get());
        model.addAttribute("medServices", medService.findAll());
        return "doctors/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDoctor(@ModelAttribute("doctor") DoctorDto doctor, RedirectAttributes redirectAttributes, @PathVariable Long id) {
        Doctor d1 = doctorService.save(doctor);;
        return "redirect:/doctors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        doctorService.delete(id);
        return "redirect:/doctors";
    }




}
