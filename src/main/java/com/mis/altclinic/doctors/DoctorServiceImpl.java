package com.mis.altclinic.doctors;

import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(DoctorDto doctor) {
        Doctor d1 = new Doctor();
        d1.setFirst_name(doctor.getFirst_name());
        d1.setLast_name(doctor.getLast_name());
        d1.setEmail(doctor.getEmail());
        d1.setPassword(passwordEncoder.encode(doctor.getPassword()));
        d1.setPatronymic(doctor.getPatronymic());
        d1.setPhone_number(doctor.getPhone_number());
        d1.setAge(doctor.getAge());
        d1.setSpecialty(doctor.getSpecialty());
        d1.setEducation(doctor.getEducation());
        d1.setExperience(doctor.getExperience());
        d1.setMedServices(doctor.getMedServices());
        d1.setRole(Role.ROLE_DOCTOR);
        d1.setEnabled(true);
        return doctorRepository.save(d1);
    }

    public Doctor save(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    @Override
    public void saveAll(List<DoctorDto> doctors) {
        for (DoctorDto doctor : doctors)
            save(doctor);
    }
    @Override
    public void saveAll2(List<Doctor> doctors) {
        for( Doctor doctor : doctors)
            save(doctor);
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        if (doctor == null)
            return null;
        Optional<Doctor> check = findById(id);
        if (check.isEmpty())
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
        if (doctorRepository.existsById(id))
            doctorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    @Override
    public List<MedService> addMedService(Long id, MedService medService) {
        Optional<Doctor> doctor = findById(id);
        if (doctor.isEmpty())
            return null;
        doctor.get().getMedServices().add(medService);
        save(doctor.get());
        return doctor.get().getMedServices();
    }
}
