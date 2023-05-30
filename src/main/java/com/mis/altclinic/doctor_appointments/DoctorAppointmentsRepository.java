package com.mis.altclinic.doctor_appointments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointment, Long> {

}
