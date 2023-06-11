package com.mis.altclinic.doctor_appointments;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.doctors.Doctor;
import com.mis.altclinic.doctors.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorService doctorService;

    @Override
    public Optional<DoctorAppointment> findById(Long id) {
        log.info("IN DoctorAppointmentServiceImpl findById {}", id);
        return doctorAppointmentRepository.findById(id);
    }

    @Override
    public List<DoctorAppointment> findAll() {
        log.info("IN DoctorAppointmentServiceImpl findAll");
        return doctorAppointmentRepository.findAll();
    }

    @Override
    public DoctorAppointment save(DoctorAppointment doctorAppointment) {
        log.info("IN DoctorAppointmentServiceImpl save {}", doctorAppointment);
        return doctorAppointmentRepository.save(doctorAppointment);
    }

    @Override
    public DoctorAppointment save(DoctorAppointmentDto doctorAppointmentDto) {
        log.info("IN DoctorAppointmentServiceImpl save {}", doctorAppointmentDto);
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setConsumer(doctorAppointmentDto.getConsumer());
        doctorAppointment.setDoctor(doctorAppointmentDto.getDoctor());
        doctorAppointment.setDate(doctorAppointmentDto.getDate_time().toLocalDate());
        doctorAppointment.setTime(doctorAppointmentDto.getDate_time().toLocalTime());
        doctorAppointment.setComment(doctorAppointmentDto.getComment());
        doctorAppointment.setPrice(doctorAppointmentDto.getPrice());
        return doctorAppointmentRepository.save(doctorAppointment);
    }

    @Override
    public void saveAll(List<DoctorAppointment> doctorAppointments) {
        log.info("IN DoctorAppointmentServiceImpl saveAll {}", doctorAppointments);
        doctorAppointmentRepository.saveAll(doctorAppointments);
    }

    @Override
    public void delete(Long id) {
        log.info("IN DoctorAppointmentServiceImpl delete {}", id);
        DoctorAppointment doctorAppointment = doctorAppointmentRepository.findById(id).get();
        doctorAppointment.setDoctor(null);
        doctorAppointment.setConsumer(null);
        doctorAppointmentRepository.save(doctorAppointment);

        doctorAppointmentRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<DoctorAppointment> doctorAppointments) {
        log.info("IN DoctorAppointmentServiceImpl deleteAll {}", doctorAppointments);
        for(DoctorAppointment doctorAppointment : doctorAppointments) {
            delete(doctorAppointment.getId());
        }
    }

    @Override
    public void deleteAll() {
        log.info("IN DoctorAppointmentServiceImpl deleteAll");
        doctorAppointmentRepository.deleteAll();
    }

    @Override
    public DoctorAppointment update(Long id, DoctorAppointment doctorAppointment) {
        log.info("IN DoctorAppointmentServiceImpl update {}", doctorAppointment);
        Optional<DoctorAppointment> doctorAppointmentOptional = doctorAppointmentRepository.findById(id);
        if(doctorAppointmentOptional.isPresent()) {
            DoctorAppointment doctorAppointment1 = doctorAppointmentOptional.get();
            doctorAppointment1.setConsumer(doctorAppointment.getConsumer());
            doctorAppointment1.setDoctor(doctorAppointment.getDoctor());
            doctorAppointment1.setDate(doctorAppointment.getDate());
            doctorAppointment1.setTime(doctorAppointment.getTime());
            doctorAppointment1.setPrice(doctorAppointment.getPrice());
            return doctorAppointmentRepository.save(doctorAppointment1);
        }
        return doctorAppointmentRepository.save(doctorAppointment);
    }

    @Override
    public DoctorAppointment update(Long id, DoctorAppointmentDto doctorAppointmentDto) {
        log.info("IN DoctorAppointmentServiceImpl update {}", doctorAppointmentDto);
        Optional<DoctorAppointment> doctorAppointmentOptional = doctorAppointmentRepository.findById(id);
        if(doctorAppointmentOptional.isPresent()) {
            DoctorAppointment doctorAppointment1 = doctorAppointmentOptional.get();
            doctorAppointment1.setConsumer(doctorAppointmentDto.getConsumer());
            doctorAppointment1.setDoctor(doctorAppointmentDto.getDoctor());
            doctorAppointment1.setDate(doctorAppointmentDto.getDate_time().toLocalDate());
            doctorAppointment1.setTime(doctorAppointmentDto.getDate_time().toLocalTime());
            doctorAppointment1.setComment(doctorAppointmentDto.getComment());
            doctorAppointment1.setPrice(doctorAppointmentDto.getPrice());
            return doctorAppointmentRepository.save(doctorAppointment1);
        }
        return save(doctorAppointmentDto);
    }

    @Override
    public List<DoctorAppointment> showForDoctor(Long id) {
        log.info("IN DoctorAppointmentServiceImpl showForDoctor {}", id);
        return doctorAppointmentRepository.findByDoctorId(id);
    }

    @Override
    public List<LocalTime> findFreeTimeFields(long doctorId, LocalDate appointmentDate) {
        List<LocalTime> bookedTimeFields = doctorAppointmentRepository.findAllByDoctorIdAndDate(doctorId, appointmentDate)
                .stream()
                .map(DoctorAppointment::getTime)
                .toList();

        return getAllTimeFields(doctorId)
                .stream()
                .filter(timeField -> !bookedTimeFields.contains(timeField))
                .toList();
    }

    @Override
    public List<Doctor> getAvailableDoctorsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Consumer consumer = (Consumer) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        List<Doctor> unavailableDoctors = doctorAppointmentRepository.findByConsumer_IdAndDateAfterAndTimeBefore(consumer.getId(), now.toLocalDate(), now.toLocalTime())
                .stream()
                .map(DoctorAppointment::getDoctor)
                .toList();
        return doctorService.findAll()
                .stream()
                .filter(doctor -> !unavailableDoctors.contains(doctor))
                .toList();
    }

    @Override
    public List<DoctorAppointment> showForConsumer(Long id) {
        log.info("IN DoctorAppointmentServiceImpl showForConsumer {}", id);
        return doctorAppointmentRepository.findByConsumerId(id);
    }


    private List<LocalTime> getAllTimeFields(long doctorId) {
        Optional<Doctor> doctor = doctorService.findById(doctorId);
        List<LocalTime> appointmentTimes = new ArrayList<>();
        if (doctor.isEmpty())
            return appointmentTimes;

        LocalTime workDayStartTime = LocalTime.of(9, 0);
        LocalTime workDayEndTime = LocalTime.of(17, 0);
        int appointmentDurationMinutes = doctor.get().getMinsOfAppointment();


        LocalTime currentTime = workDayStartTime;
        while (currentTime.plusMinutes(appointmentDurationMinutes).isBefore(workDayEndTime)) {
            appointmentTimes.add(currentTime);
            currentTime = currentTime.plusMinutes(appointmentDurationMinutes);
        }

        return appointmentTimes;
    }
}

