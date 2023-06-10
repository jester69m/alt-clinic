package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findById(Long id);
    List<Doctor> findAll();

    Doctor save(DoctorDto doctor);
    Doctor save(Doctor doctor);
    void saveAll(List<DoctorDto> doctors);
    void saveAll2(List<Doctor> doctors);
    Doctor update(Long id, DoctorDto doctor);
    void delete(Long id);
    void deleteAll();

    List<MedService> addMedService(Long id, MedService medService);

    List<String> getAllSpecialities();
}
