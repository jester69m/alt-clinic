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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Consumer consumer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Doctor doctor;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date_time;

    private String comment;

    private Double price;

    public DoctorAppointment(Consumer consumer,
                             Doctor doctor,
                             LocalDateTime date_time,
                             String comment,
                             Double price) {
        this.consumer = consumer;
        this.doctor = doctor;
        this.date_time = date_time;
        this.comment = comment;
        this.price = price;
    }
}
