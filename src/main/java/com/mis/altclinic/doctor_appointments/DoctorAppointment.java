package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctors.Doctor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DoctorAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Consumer consumer;

    @ManyToOne
    private Doctor doctor;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date_time;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String comment;

    private Double price;

    public DoctorAppointment(Consumer consumer,
                             Doctor doctor,
                             LocalDateTime date_time,
                             Status status,
                             String comment,
                             Double price) {
        this.consumer = consumer;
        this.doctor = doctor;
        this.date_time = date_time;
        this.status = status;
        this.comment = comment;
        this.price = price;
    }
}
