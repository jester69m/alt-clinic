package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findById(Long id);
    List<Doctor> findAll();

    Doctor save(Doctor doctor);
    void saveAll(List<Doctor> doctors);
    Doctor update(Long id, Doctor doctor);
    void delete(Long id);

    List<MedService> addMedService(Long id, MedService medService);

}
