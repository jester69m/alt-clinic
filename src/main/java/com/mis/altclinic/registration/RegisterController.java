package com.mis.altclinic.registration;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.consumers.ConsumerService;
import com.mis.altclinic.doctors.DoctorDto;
import com.mis.altclinic.doctors.DoctorService;
import com.mis.altclinic.medservices.MedServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final DoctorService doctorService;
    private final ConsumerService consumerService;
    private final MedServiceService medServiceService;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("role", "");
        return "register/choose-role";
    }

    @PostMapping
    public String takeRole(
            @RequestParam("role") String role) {
        if(role.equals("doctor")) {
            return "redirect:/register/doctor";
        }
        if(role.equals("consumer")) {
            return "redirect:/register/consumer";
        }
        return "redirect:/register";
    }

    @GetMapping("/doctor")
    public String showDoctorRegisterForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        model.addAttribute("medServices", medServiceService.findAll());
        return "register/doctor";
    }

    @PostMapping("/doctor")
    public String registerDoctor(@ModelAttribute DoctorDto doctor) {
        doctorService.save(doctor);
        return "redirect:/login";
    }

    @GetMapping("/consumer")
    public String showConsumerRegisterForm(Model model) {
        model.addAttribute("consumer", new Consumer());
        return "register/consumer";
    }

    @PostMapping("/consumer")
    public String registerConsumer(@ModelAttribute Consumer consumer) {
        consumerService.save(consumer);
        return "redirect:/login";
    }
}
