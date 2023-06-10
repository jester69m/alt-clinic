package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctors.Doctor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    private String comment;

    private Double price;

    public DoctorAppointment(Consumer consumer,
                             Doctor doctor,
                             LocalDateTime dateTime,
                             String comment,
                             Double price) {
        this.consumer = consumer;
        this.doctor = doctor;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
        this.comment = comment;
        this.price = price;
    }
}
