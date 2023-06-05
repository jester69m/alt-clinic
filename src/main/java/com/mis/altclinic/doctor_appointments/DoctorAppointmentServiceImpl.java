package com.mis.altclinic.doctor_appointments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private final DoctorAppointmentsRepository doctorAppointmentsRepository;

    @Override
    public Optional<DoctorAppointment> findById(Long id) {
        return doctorAppointmentsRepository.findById(id);
    }

    @Override
    public List<DoctorAppointment> findAll() {
        return doctorAppointmentsRepository.findAll();
    }

    @Override
    public DoctorAppointment save(DoctorAppointment doctorAppointment) {
        return doctorAppointmentsRepository.save(doctorAppointment);
    }

    @Override
    public DoctorAppointment save(DoctorAppointmentDto doctorAppointmentDto) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setConsumer(doctorAppointmentDto.getConsumer());
        doctorAppointment.setDoctor(doctorAppointmentDto.getDoctor());
        doctorAppointment.setDate_time(doctorAppointmentDto.getDate_time());
        doctorAppointment.setStatus(doctorAppointmentDto.getStatus());
        doctorAppointment.setComment(doctorAppointmentDto.getComment());
        doctorAppointment.setPrice(doctorAppointmentDto.getPrice());
        return doctorAppointmentsRepository.save(doctorAppointment);
    }

    @Override
    public void saveAll(List<DoctorAppointment> doctorAppointments) {
        doctorAppointmentsRepository.saveAll(doctorAppointments);
    }

    @Override
    public void delete(Long id) {
        doctorAppointmentsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        doctorAppointmentsRepository.deleteAll();
    }

    @Override
    public DoctorAppointment update(Long id, DoctorAppointment doctorAppointment) {
        Optional<DoctorAppointment> doctorAppointmentOptional = doctorAppointmentsRepository.findById(id);
        if(doctorAppointmentOptional.isPresent()){
            DoctorAppointment doctorAppointment1 = doctorAppointmentOptional.get();
            doctorAppointment1.setConsumer(doctorAppointment.getConsumer());
            doctorAppointment1.setDoctor(doctorAppointment.getDoctor());
            doctorAppointment1.setDate_time(doctorAppointment.getDate_time());
            doctorAppointment1.setStatus(doctorAppointment.getStatus());
            doctorAppointment1.setPrice(doctorAppointment.getPrice());
            return doctorAppointmentsRepository.save(doctorAppointment1);
        }
        return doctorAppointmentsRepository.save(doctorAppointment);
    }

    @Override
    public DoctorAppointment update(Long id, DoctorAppointmentDto doctorAppointmentDto) {
        Optional<DoctorAppointment> doctorAppointmentOptional = doctorAppointmentsRepository.findById(id);
        if(doctorAppointmentOptional.isPresent()){
            DoctorAppointment doctorAppointment1 = doctorAppointmentOptional.get();
            doctorAppointment1.setConsumer(doctorAppointmentDto.getConsumer());
            doctorAppointment1.setDoctor(doctorAppointmentDto.getDoctor());
            doctorAppointment1.setDate_time(doctorAppointmentDto.getDate_time());
            doctorAppointment1.setStatus(doctorAppointmentDto.getStatus());
            doctorAppointment1.setComment(doctorAppointmentDto.getComment());
            doctorAppointment1.setPrice(doctorAppointmentDto.getPrice());
            return doctorAppointmentsRepository.save(doctorAppointment1);
        }
        return save(doctorAppointmentDto);
    }
}

