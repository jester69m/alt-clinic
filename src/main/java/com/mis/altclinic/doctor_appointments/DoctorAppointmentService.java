package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctors.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
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

    void deleteAll(List<DoctorAppointment> doctorAppointments);

    DoctorAppointment update(Long id, DoctorAppointment doctorAppointment);

    DoctorAppointment update(Long id, DoctorAppointmentDto doctorAppointmentDto);

    List<DoctorAppointment> showForConsumer(Long id);

    List<DoctorAppointment> showForDoctor(Long id);

    List<LocalTime> findFreeTimeFields(long doctorId, LocalDate appointmentDate);

    List<Doctor> getAvailableDoctorsForUser();

    boolean existsByConsumerIdAndDoctorId(Long consumerId, Long doctorId);

}
