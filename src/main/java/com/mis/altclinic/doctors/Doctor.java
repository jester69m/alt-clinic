package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {

    private String first_name;

    private String last_name;

    private String patronymic;

    @Column(length = 13)
    private String phone_number;

    @Positive
    private Integer age;

    private String specialty;

    private String education;

    private String experience;

    private int minsOfAppointment = 30;

    @ManyToMany
    @Cascade(CascadeType.REMOVE)
    private List<MedService> medServices;


    public Doctor(String first_name,
                  String last_name,
                  String email,
                  String password,
                  String patronymic,
                  String phone_number,
                  int age,
                  String specialty,
                  String education,
                  String experience,
                  List<MedService> medServices) {
        super(email, password, Role.ROLE_DOCTOR);
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.age = age;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
        this.medServices = medServices;
    }
}
