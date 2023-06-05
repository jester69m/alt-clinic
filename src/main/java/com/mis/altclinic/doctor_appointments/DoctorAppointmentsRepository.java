package com.mis.altclinic.doctor_appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointment, Long> {

}
