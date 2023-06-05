package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {

    private String patronymic;

    @Column(length = 13)
    private String phone_number;

    @Positive
    private Integer age;

    private String specialty;

    private String education;

    private String experience;

    @OneToMany
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
        super(first_name,last_name, email, password, Role.ROLE_DOCTOR, true);
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.age = age;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
        this.medServices = medServices;
    }
}
