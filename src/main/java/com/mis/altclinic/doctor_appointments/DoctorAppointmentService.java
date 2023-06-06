package com.mis.altclinic.doctor_appointments;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DoctorAppointmentService {

    Optional<DoctorAppointment> findById(Long id);
    List<DoctorAppointment> findAll();

    DoctorAppointment save(DoctorAppointment doctorAppointment);
    DoctorAppointment save(DoctorAppointmentDto doctorAppointmentDto);
    void saveAll(List<DoctorAppointment> doctorAppointments);

    void delete(Long id);
    void deleteAll();

    DoctorAppointment update(Long id, DoctorAppointment doctorAppointment);
    DoctorAppointment update(Long id, DoctorAppointmentDto doctorAppointmentDto);

    List<DoctorAppointment> showForConsumer(Long id);
    List<DoctorAppointment> showForDoctor(Long id);
}
