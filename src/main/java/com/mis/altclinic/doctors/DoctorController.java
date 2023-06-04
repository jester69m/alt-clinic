package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final MedServiceRepository medService;

    @GetMapping("/doctors")
    public String showAll(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        return "doctors/doctors";
    }

    @GetMapping("/doctors/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("medServices", medService.findAll());
        return "doctors/add-doctor";
    }

    @PostMapping("/doctors/add")
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor, RedirectAttributes redirectAttributes) {
        try {
            doctorService.save(doctor);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while adding the doctor.");
            return "redirect:/doctors/add";
        }
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/{id}/edit")
    public String showEditDoctorForm(Model model, @PathVariable Long id) {
        model.addAttribute("doctor", doctorService.findById(id));
        model.addAttribute("medServices", medService.findAll());
        return "doctors/edit-doctor";
    }

    @PostMapping("/doctors/{id}/edit")
    public String editDoctor(@ModelAttribute("doctor") Doctor doctor, RedirectAttributes redirectAttributes) {
        try {
            doctorService.save(doctor);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while editing the doctor.");
            return "redirect:/doctors/" + doctor.getId() + "/edit";
        }
        return "redirect:/doctors";
    }

    @DeleteMapping("/doctors/{id}/delete")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            doctorService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while deleting the doctor.");
            return "redirect:/doctors";
        }
        return "redirect:/doctors";
    }




}
