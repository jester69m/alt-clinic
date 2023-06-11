package com.mis.altclinic.doctor_appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {

    List<DoctorAppointment> findByDoctorId(Long id);

    List<DoctorAppointment> findByConsumerId(Long id);

    List<DoctorAppointment> findAllByDoctorIdAndDate(Long doctorId, LocalDate appointmentDate);

    @Query("select d from DoctorAppointment d WHERE d.consumer.id = ?1 AND d.date >= ?2 AND d.time >= ?3")
    List<DoctorAppointment> findByConsumer_IdAndDateAfterAndTimeBefore(Long id, LocalDate date, LocalTime time);

    List<DoctorAppointment> findAllByDoctorId(Long id);

    List<DoctorAppointment> findAllByConsumerId(Long id);
}
