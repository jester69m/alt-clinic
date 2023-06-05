package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctor_appointments.Status;
import com.mis.altclinic.doctors.Doctor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointmentDto {

    Long id;
    @OneToOne
    Consumer consumer;
    @OneToOne
    Doctor doctor;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime date_time;
    @Enumerated(EnumType.STRING)
    Status status;
    String comment;
    Double price;
}
