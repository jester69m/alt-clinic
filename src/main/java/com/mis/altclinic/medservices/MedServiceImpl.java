package com.mis.altclinic.medservices;

import com.mis.altclinic.doctors.Doctor;
import com.mis.altclinic.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedServiceImpl implements MedServiceService{

    private final MedServiceRepository medServiceRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public MedService save(MedService medService) {
        return medServiceRepository.save(medService);
    }

    @Override
    public Optional<MedService> findById(Long id) {
        return medServiceRepository.findById(id);
    }

    @Override
    public List<MedService> findAll() {
        return medServiceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        List<Doctor> doctors = doctorRepository.findAllByMedServices_id(id);
        for (Doctor doctor : doctors) {
            doctor.getMedServices().removeIf(medService -> medService.getId().equals(id));
            doctorRepository.save(doctor);
        }
        medServiceRepository.deleteById(id);
    }
}
