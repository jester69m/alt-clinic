package com.mis.altclinic.doctor_appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointment, Long> {

    List<DoctorAppointment> findByDoctorId(Long id);
    List<DoctorAppointment> findByConsumerId(Long id);
}
