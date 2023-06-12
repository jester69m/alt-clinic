package com.mis.altclinic.doctors;

import com.mis.altclinic.doctor_appointments.DoctorAppointment;
import com.mis.altclinic.doctor_appointments.DoctorAppointmentRepository;
import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.users.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Doctor> findById(Long id) {
        log.info("IN DoctorServiceImpl findById {}", id);
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        log.info("IN DoctorServiceImpl findAll");
        return doctorRepository.findAll();
    }

    @Override
    public Doctor save(DoctorDto doctor) {
        log.info("IN DoctorServiceImpl save {}", doctor);
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
        log.info("IN DoctorServiceImpl save {}", doctor);
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    @Override
    public void saveAll(List<DoctorDto> doctors) {
        log.info("IN DoctorServiceImpl saveAll {}", doctors);
        for (DoctorDto doctor : doctors)
            save(doctor);
    }
    @Override
    public void saveAll2(List<Doctor> doctors) {
        log.info("IN DoctorServiceImpl saveAll {}", doctors);
        for(Doctor doctor : doctors)
            save(doctor);
    }

    @Override
    public void update(Long id, DoctorDto doctor) {
        log.info("IN DoctorServiceImpl update {}", doctor);
        Optional<Doctor> check = findById(id);
        if (check.isEmpty())
            return;
        Doctor updated = check.get();
        updated.setFirst_name(doctor.getFirst_name());
        updated.setLast_name(doctor.getLast_name());
        updated.setPatronymic(doctor.getPatronymic());
        if(doctor.getEmail() != null)
            updated.setEmail(doctor.getEmail());
        if(doctor.getPassword() != null)
            updated.setPassword(doctor.getPassword());
        updated.setPhone_number(doctor.getPhone_number());
        updated.setAge(doctor.getAge());
        updated.setSpecialty(doctor.getSpecialty());
        updated.setEducation(doctor.getEducation());
        updated.setExperience(doctor.getExperience());
        if(doctor.getMedServices() != null)
            updated.setMedServices(doctor.getMedServices());

        doctorRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        log.info("IN DoctorServiceImpl delete {}", id);
        List<DoctorAppointment> doctorAppointments = doctorAppointmentRepository.findAllByDoctorId(id);
        for (DoctorAppointment doctorAppointment : doctorAppointments){
            doctorAppointment.setDoctor(null);
            doctorAppointment.setConsumer(null);
            doctorAppointmentRepository.save(doctorAppointment);
        }

        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.setMedServices(null);
        doctorRepository.save(doctor);

        doctorRepository.deleteById(id);
        doctorAppointmentRepository.deleteAll(doctorAppointments);
    }

    @Override
    public void deleteAll() {
        log.info("IN DoctorServiceImpl deleteAll");
        List<Doctor> doctors = findAll();
        for (Doctor doctor : doctors)
            delete(doctor.getId());
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

    @Override
    public List<String> getAllSpecialities() {
        log.info("IN DoctorServiceImpl getAllSpecialities");
        return doctorRepository.findDistinctSpecialities();
    }
}
