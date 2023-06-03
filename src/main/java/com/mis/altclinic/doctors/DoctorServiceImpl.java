package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void saveAll(List<Doctor> doctors) {
        doctorRepository.saveAll(doctors);
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        if(doctor == null)
            return null;
        Optional<Doctor> check = findById(id);
        if(check.isEmpty())
            return null;
        Doctor updated = check.get();
        updated.setFirst_name(doctor.getFirst_name());
        updated.setLast_name(doctor.getLast_name());
        updated.setPatronymic(doctor.getPatronymic());
        updated.setEmail(doctor.getEmail());
//        updated.setPassword(doctor.getPassword());
        updated.setRole(doctor.getRole());
        updated.setEnabled(doctor.getEnabled());
        updated.setPhone_number(doctor.getPhone_number());
        updated.setAge(doctor.getAge());
        updated.setSpecialty(doctor.getSpecialty());
        updated.setEducation(doctor.getEducation());
        updated.setExperience(doctor.getExperience());

        return doctorRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        if(doctorRepository.existsById(id))
            doctorRepository.deleteById(id);

    }

    @Override
    public List<MedService> addMedService(Long id, MedService medService) {
        Optional<Doctor> doctor = findById(id);
        if(doctor.isEmpty())
            return null;
        doctor.get().getMedServices().add(medService);
        save(doctor.get());
        return doctor.get().getMedServices();
    }
}
