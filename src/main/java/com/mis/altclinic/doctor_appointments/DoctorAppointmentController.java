package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor_appointments")
public class DoctorAppointmentController {

    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAll(Model model) {
        List<DoctorAppointment> appointments = doctorAppointmentService.findAll();
        model.addAttribute("appointments", appointments);
        return "doctor_appointments/list";
    }

    @GetMapping("/doctor/{id}")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public String showForDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctorAppointment", doctorAppointmentService.showForDoctor(id));
        return "doctor_appointments/list";
    }

    @GetMapping("/consumer")
    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    public String showForConsumer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Consumer consumer = (Consumer) authentication.getPrincipal();

        long consumerId = consumer.getId();

        model.addAttribute("doctorAppointment", doctorAppointmentService.showForConsumer(consumerId));
        return "doctor_appointments/consumer";
    }

    @GetMapping("/add")
    public String addDoctorAppointmentForm(Model model) {
        model.addAttribute("doctorAppointment", new DoctorAppointmentDto());
        return "doctor_appointments/add";
    }

    @PostMapping("/add")
    public String addDoctorAppointment(DoctorAppointmentDto doctorAppointment) {
        doctorAppointmentService.save(doctorAppointment);
        return "redirect:/doctor_appointments";
    }

    @GetMapping("/edit/{id}")
    public String showEditDoctorAppointmentForm(Long id, Model model) {
        model.addAttribute("doctorAppointment", doctorAppointmentService.findById(id).get());
        return "doctor_appointments/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDoctorAppointment(DoctorAppointmentDto doctorAppointment, @PathVariable Long id) {
        doctorAppointmentService.update(id, doctorAppointment);
        return "redirect:/doctor_appointments/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctorAppointment(@PathVariable Long id) {
        doctorAppointmentService.delete(id);
        return "redirect:/doctor_appointments/list";
    }
}
