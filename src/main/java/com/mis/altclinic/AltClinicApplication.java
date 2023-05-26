package com.mis.altclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class AltClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltClinicApplication.class, args);
	}

}
