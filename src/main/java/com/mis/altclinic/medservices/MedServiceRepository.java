package com.mis.altclinic.medservices;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedServiceRepository extends JpaRepository<MedService, Long> {
}
