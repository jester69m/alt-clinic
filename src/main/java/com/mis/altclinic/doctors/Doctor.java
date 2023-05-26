package com.mis.altclinic.doctors;

import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends User {

    private String patronymic;

    @Column(length = 13)
    private String phone_number;

    @Positive
    private Integer age;

    private String specialty;

    private String education;

    private String experience;


    public Doctor(String first_name,
                  String last_name,
                  String email,
                  String password,
                  Role role,
                  String patronymic,
                  String phone_number,
                  Integer age,
                  String specialty,
                  String education,
                  String experience,
                  Boolean enabled) {
        super(first_name,last_name, email, password, role, enabled);
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.age = age;
        this.specialty = specialty;
        this.education = education;
        this.experience = experience;
    }
}
