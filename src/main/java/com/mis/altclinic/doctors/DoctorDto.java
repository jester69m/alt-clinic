package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DoctorDto {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String patronymic;
    private String phone_number;
    private int age;
    private String specialty;
    private String education;
    private String experience;
    private List<MedService> medServices;

    public DoctorDto() {

    }
}
