package com.mis.altclinic.doctor_appointments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChooseDoctorDateRequest {
    @JsonProperty("doctor_id")
    private Long doctorId;
    @JsonProperty("date")
    private LocalDate appointmentDate;
}
