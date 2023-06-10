package com.mis.altclinic.doctors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findDistinctSpecialities();
}
