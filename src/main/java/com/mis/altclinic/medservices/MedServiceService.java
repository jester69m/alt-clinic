package com.mis.altclinic.medservices;

import java.util.List;
import java.util.Optional;

public interface MedServiceService {

    MedService save(MedService medService);

    Optional<MedService> findById(Long id);

    List<MedService> findAll();

    void deleteById(Long id);
}
