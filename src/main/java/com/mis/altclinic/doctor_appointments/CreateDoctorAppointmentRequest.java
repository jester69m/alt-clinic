package com.mis.altclinic.doctor_appointments;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateDoctorAppointmentRequest {
    private LocalTime time;
    private String comment;
    private Long medServiceId;
}
