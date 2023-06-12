package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctors.Doctor;
import com.mis.altclinic.doctors.DoctorService;
import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.medservices.MedServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor_appointments")
@SessionAttributes("doctorDate")
public class DoctorAppointmentController {

    private final DoctorAppointmentService doctorAppointmentService;
    private final DoctorService doctorService;
    private final MedServiceRepository medServiceRepository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAll(Model model) {
        List<DoctorAppointment> appointments = doctorAppointmentService.findAll();
        model.addAttribute("appointments", appointments);
        return "doctor_appointments/list";
    }
    @GetMapping("/add")
    public String showChoseDoctorDateForm(Model model) {
        model.addAttribute("doctors", doctorAppointmentService.getAvailableDoctorsForUser());
        model.addAttribute("doctorAppointments", doctorAppointmentService.findAll());
        model.addAttribute("specialities", doctorService.getAllSpecialities());
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("doctorDate", new ChooseDoctorDateRequest());
        return "doctor_appointments/choose_doctor_date";
    }

    @PostMapping("/choose-doctor-date")
    public String showDoctorTimeFields(ChooseDoctorDateRequest doctorAppointment,
                                       Model model) {
        if (doctorAppointment.getDoctorId() == null) {
            model.addAttribute("error", "Please select a doctor");
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("specialities", doctorService.getAllSpecialities());
            model.addAttribute("minDate", LocalDate.now());
            return "doctor_appointments/choose_doctor_date";
        }

        List<LocalTime> freeTimeFields = doctorAppointmentService.findFreeTimeFields(doctorAppointment.getDoctorId(), doctorAppointment.getAppointmentDate());
        model.addAttribute("doctorDate", doctorAppointment);

        if (freeTimeFields.size() == 0) {
            model.addAttribute("error", "No free time field for this doctor");
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("specialities", doctorService.getAllSpecialities());
            model.addAttribute("minDate", LocalDate.now());
            return "doctor_appointments/choose_doctor_date";
        }

        Doctor doctor = doctorService.findById(doctorAppointment.getDoctorId()).get();
        model.addAttribute("freeTimeFields", freeTimeFields);
        model.addAttribute("doctorAppointment", new CreateDoctorAppointmentRequest());
        model.addAttribute("mesServices", doctor.getMedServices());
        return "doctor_appointments/add";
    }

    @PostMapping("/add")
    public String addDoctorAppointment(CreateDoctorAppointmentRequest doctorAppointment, Model model) {
        ChooseDoctorDateRequest doctorDate = (ChooseDoctorDateRequest) model.getAttribute("doctorDate");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Consumer consumer = (Consumer) authentication.getPrincipal();
        Doctor doctor = doctorService.findById(doctorDate.getDoctorId()).get();
        LocalDateTime dateTime = LocalDateTime.of(doctorDate.getAppointmentDate(), doctorAppointment.getTime());
        MedService medService = medServiceRepository.findById(doctorDate.getDoctorId()).get();

        // Check if the consumer already has an appointment with the selected doctor
        boolean hasExistingAppointment = doctorAppointmentService.existsByConsumerIdAndDoctorId(consumer.getId(), doctor.getId());

        if (hasExistingAppointment) {
            model.addAttribute("error", "You already have an appointment with this doctor");
            model.addAttribute("doctors", doctorAppointmentService.getAvailableDoctorsForUser());
            model.addAttribute("specialities", doctorService.getAllSpecialities());
            model.addAttribute("minDate", LocalDate.now());
            model.addAttribute("doctorDate", doctorDate);
            return "doctor_appointments/choose_doctor_date";
        }

        doctorAppointmentService.save(new DoctorAppointment(
                consumer,
                doctor,
                dateTime,
                doctorAppointment.getComment(),
                medService.getPrice()
        ));
        return "redirect:/doctor_appointments/consumer";
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



    @GetMapping("/consumer")
    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    public String showForConsumer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Consumer consumer = (Consumer) authentication.getPrincipal();

        long consumerId = consumer.getId();

        model.addAttribute("doctorAppointment", doctorAppointmentService.showForConsumer(consumerId));
        return "doctor_appointments/consumer";
    }

    @GetMapping("/consumer/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    public String showEditForm(@PathVariable Long id, Model model) {
        DoctorAppointment doctorAppointment = doctorAppointmentService.findById(id).get();
        model.addAttribute("doctorAppointment", doctorAppointment);
        return "redirect:/doctor_appointments/consumer";
    }
    @PostMapping("/consumer/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    public String editDoctorAppointment(@PathVariable Long id, @ModelAttribute DoctorAppointment doctorAppointment) {
        doctorAppointmentService.update(id, doctorAppointment);
        return "redirect:/doctor_appointments/consumer";
    }

    @GetMapping("/consumer/delete/{id}")
    @PreAuthorize("hasRole('CONSUMER')")
    public String deleteDoctorAppointmentForConsumer(@PathVariable Long id) {
        doctorAppointmentService.delete(id);
        return "redirect:/doctor_appointments/consumer";
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public String showForDoctor(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor = (Doctor) authentication.getPrincipal();

        long id = doctor.getId();

        model.addAttribute("doctorAppointment", doctorAppointmentService.showForDoctor(id));
        return "doctor_appointments/doctor";
    }

    @GetMapping("/doctor/delete/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public String deleteDoctorAppointmentForDoctor(@PathVariable Long id) {
        doctorAppointmentService.delete(id);
        return "redirect:/doctor_appointments/doctor";
    }



}
