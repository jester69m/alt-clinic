package com.mis.altclinic.doctors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    UserDetails findByEmail(String email);
}
