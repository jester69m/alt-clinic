package com.mis.altclinic.util;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.consumers.ConsumerService;
import com.mis.altclinic.doctor_appointments.DoctorAppointment;
import com.mis.altclinic.doctor_appointments.DoctorAppointmentService;
import com.mis.altclinic.doctor_appointments.Status;
import com.mis.altclinic.doctors.Doctor;
import com.mis.altclinic.doctors.DoctorService;
import com.mis.altclinic.medservices.MedService;
import com.mis.altclinic.medservices.MedServiceRepository;
import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import com.mis.altclinic.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PopulateDB {

private final MedServiceRepository medServiceRepository;
private final DoctorService doctorService;
private final ConsumerService consumerService;
private final DoctorAppointmentService doctorAppointmentService;
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;


    @Autowired
    public PopulateDB(MedServiceRepository medServiceRepository, DoctorService doctorService, ConsumerService consumerService, DoctorAppointmentService doctorAppointmentService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.medServiceRepository = medServiceRepository;
        this.doctorService = doctorService;
        this.consumerService = consumerService;
        this.doctorAppointmentService = doctorAppointmentService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.

        populate();
    }

    public void populate() {
        medServiceRepository.deleteAll();
        doctorService.deleteAll();
        consumerService.deleteAll();
        doctorAppointmentService.deleteAll();

        MedService ms1 = new MedService("Cardiology", "Cardiology is a branch of medicine that deals with the disorders of the heart as well as some parts of the circulatory system.", 100.0);
        MedService ms2 = new MedService("Dermatology", "Dermatology is the branch of medicine dealing with the skin, nails, hair and its diseases.", 100.0);
        MedService ms3 = new MedService("Endocrinology", "Endocrinology is a branch of biology and medicine dealing with the endocrine system, its diseases, and its specific secretions known as hormones.", 100.0);
        MedService ms4 = new MedService("Gastroenterology", "Gastroenterology is the branch of medicine focused on the digestive system and its disorders.", 100.0);
        MedService ms5 = new MedService("Hematology", "Hematology, also spelled haematology, is the branch of medicine concerned with the study of the cause, prognosis, treatment, and prevention of diseases related to blood.", 100.);
        MedService ms6 = new MedService("Infectious Disease", "Infectious diseases, also known as infectiology, is a medical specialty dealing with the diagnosis, control and treatment of infections.", 100.0);
        MedService ms7 = new MedService("Nephrology", "Nephrology is a specialty of medicine and pediatrics that concerns itself with the kidneys: the study of therapy.", 100.0);
        MedService ms8 = new MedService("Neurology", "Neurology is a branch of medicine dealing with disorders of the nervous system.", 100.0);
        MedService ms9 = new MedService("Oncology", "Oncology is a branch of medicine that deals with the prevention, diagnosis, and treatment of cancer.", 100.0);
        MedService ms10 = new MedService("Pulmonology", "Pulmonology is a medical speciality that deals with diseases involving the respiratory tract.", 100.0);
        MedService ms11 = new MedService("Rheumatology", "Rheumatology is a branch of medicine devoted to the diagnosis and therapy of rheumatic diseases.", 100.0);

        List<MedService> medServices = List.of(ms1, ms2, ms3, ms4, ms5, ms6, ms7, ms8, ms9, ms10, ms11);
        medServiceRepository.saveAll(medServices);

        Doctor d1 = new Doctor("DoctorFirstName1","DoctorLastName1","Doctor1@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic1", "+38103018031",21,"Cardiology","Cardiology university", "Several years", List.of(ms1));
        Doctor d2 = new Doctor("DoctorFirstName2","DoctorLastName2","Doctor2@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic2", "+38103018032",22,"Dermatology","Dermatology university", "Several years", List.of(ms2));
        Doctor d3 = new Doctor("DoctorFirstName3","DoctorLastName3","Doctor3@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic3", "+38103018033",23,"Endocrinology","Endocrinoly university", "Several years", List.of(ms3));
        Doctor d4 = new Doctor("DoctorFirstName4","DoctorLastName4","Doctor4@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic4", "+38103018034",24,"Gastroenteritis","Gastroentey university", "Several years", List.of(ms4));
        Doctor d5 = new Doctor("DoctorFirstName5","DoctorLastName5","Doctor5@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic5", "+38103018035",25,"Hematology","Hematology university", "Several years", List.of(ms5));
        Doctor d6 = new Doctor("DoctorFirstName6","DoctorLastName6","Doctor6@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic6", "+38103018036",26,"Infectious","Infectious university", "Several years", List.of(ms6));
        Doctor d7 = new Doctor("DoctorFirstName7","DoctorLastName7","Doctor7@mail.com", passwordEncoder.encode( "password"),"DoctorPatronymic7", "+38103018037",27,"Nephrology","Nephrology university", "Several years", List.of(ms7));

        List<Doctor> doctors = List.of(d1,d2,d3,d4,d5,d6,d7);
        doctorService.saveAll2(doctors);

        Consumer c1 = new Consumer();c1.setFirst_name("ConsumerFirstName1");c1.setLast_name ("ConsumerLastName1");c1.setEmail("Consumer1@mail.com");c1.setPassword(passwordEncoder.encode("password"));c1.setPatronymic("ConsumerPatronymic1");c1.setPhone_number("+38108030");c1.setAddress("ConsumerAddress1");c1.setAge(21);c1.setBlood_type("1+");
        Consumer c2 = new Consumer();c2.setFirst_name("ConsumerFirstName2");c2.setLast_name ("ConsumerLastName2");c2.setEmail("Consumer2@mail.com");c2.setPassword(passwordEncoder.encode("password"));c2.setPatronymic("ConsumerPatronymic2");c2.setPhone_number("+38108030");c2.setAddress("ConsumerAddress2");c2.setAge(22);c2.setBlood_type("2-");
        Consumer c3 = new Consumer();c3.setFirst_name("ConsumerFirstName3");c3.setLast_name ("ConsumerLastName3");c3.setEmail("Consumer3@mail.com");c3.setPassword(passwordEncoder.encode("password"));c3.setPatronymic("ConsumerPatronymic3");c3.setPhone_number("+38108030");c3.setAddress("ConsumerAddress3");c3.setAge(23);c3.setBlood_type("3-");
        Consumer c4 = new Consumer();c4.setFirst_name("ConsumerFirstName4");c4.setLast_name ("ConsumerLastName4");c4.setEmail("Consumer4@mail.com");c4.setPassword(passwordEncoder.encode("password"));c4.setPatronymic("ConsumerPatronymic4");c4.setPhone_number("+38108030");c4.setAddress("ConsumerAddress4");c4.setAge(24);c4.setBlood_type("4+");
        Consumer c5 = new Consumer();c5.setFirst_name("ConsumerFirstName5");c5.setLast_name ("ConsumerLastName5");c5.setEmail("Consumer5@mail.com");c5.setPassword(passwordEncoder.encode("password"));c5.setPatronymic("ConsumerPatronymic5");c5.setPhone_number("+38108030");c5.setAddress("ConsumerAddress5");c5.setAge(25);c5.setBlood_type("1+");
        Consumer c6 = new Consumer();c6.setFirst_name("ConsumerFirstName6");c6.setLast_name ("ConsumerLastName6");c6.setEmail("Consumer6@mail.com");c6.setPassword(passwordEncoder.encode("password"));c6.setPatronymic("ConsumerPatronymic6");c6.setPhone_number("+38108030");c6.setAddress("ConsumerAddress6");c6.setAge(26);c6.setBlood_type("2-");
        Consumer c7 = new Consumer();c7.setFirst_name("ConsumerFirstName7");c7.setLast_name ("ConsumerLastName7");c7.setEmail("Consumer7@mail.com");c7.setPassword(passwordEncoder.encode("password"));c7.setPatronymic("ConsumerPatronymic7");c7.setPhone_number("+38108030");c7.setAddress("ConsumerAddress7");c7.setAge(27);c7.setBlood_type("3-");
        Consumer c8 = new Consumer();c8.setFirst_name("ConsumerFirstName8");c8.setLast_name ("ConsumerLastName8");c8.setEmail("Consumer8@mail.com");c8.setPassword(passwordEncoder.encode("password"));c8.setPatronymic("ConsumerPatronymic8");c8.setPhone_number("+38108030");c8.setAddress("ConsumerAddress8");c8.setAge(28);c8.setBlood_type("4+");
        Consumer c9 = new Consumer();c9.setFirst_name("ConsumerFirstName9");c9.setLast_name ("ConsumerLastName9");c9.setEmail("Consumer9@mail.com");c9.setPassword(passwordEncoder.encode("password"));c9.setPatronymic("ConsumerPatronymic9");c9.setPhone_number("+38108030");c9.setAddress("ConsumerAddress9");c9.setAge(29);c9.setBlood_type("1+");

        List<Consumer> consumers = List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9);
        consumerService.saveAll(consumers);

        DoctorAppointment da1 = new DoctorAppointment(c1, d1, LocalDateTime.now().plusDays(2).minusHours(10), Status.NEW, "Some problem", 100.0);
        DoctorAppointment da2 = new DoctorAppointment(c2, d2, LocalDateTime.now().plusDays(1).minusHours(10), Status.CONFIRMED, "Some problem", 200.0);
        DoctorAppointment da3 = new DoctorAppointment(c3, d3, LocalDateTime.now().plusDays(3).minusHours(10), Status.NEW, "Some problem", 300.0);
        DoctorAppointment da4 = new DoctorAppointment(c4, d4, LocalDateTime.now().plusDays(4).minusHours(10), Status.CONFIRMED, "Some problem", 150.0);
        DoctorAppointment da5 = new DoctorAppointment(c5, d5, LocalDateTime.now().plusDays(5).minusHours(10), Status.NEW, "Some problem", 100.0);
        DoctorAppointment da6 = new DoctorAppointment(c6, d5, LocalDateTime.now().plusDays(6).minusHours(10), Status.CONFIRMED, "Some problem", 170.0);
        DoctorAppointment da7 = new DoctorAppointment(c7, d1, LocalDateTime.now().plusDays(7).minusHours(10), Status.NEW, "Some problem", 100.0);
        DoctorAppointment da8 = new DoctorAppointment(c1, d6, LocalDateTime.now().plusDays(8).minusHours(10), Status.CONFIRMED, "Some problem", 100.0);
        DoctorAppointment da9 = new DoctorAppointment(c1, d7, LocalDateTime.now().plusDays(1).minusHours(10), Status.CANCELLED, "Some problem", 180.0);
        DoctorAppointment da10 = new DoctorAppointment(c2, d6, LocalDateTime.now().plusDays(2).minusHours(10), Status.NEW, "Some problem", 1000.0);
        DoctorAppointment da11 = new DoctorAppointment(c2, d7, LocalDateTime.now().minusDays(10).minusHours(10), Status.COMPLETED, "Some problem", 10000.0);

        List<DoctorAppointment> doctorAppointments = List.of(da1,da2,da3,da4,da5,da6,da7,da8,da9,da10,da11);
        doctorAppointmentService.saveAll(doctorAppointments);

        User admin = User.builder()
                .email("admin")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(admin);

        User consumer = User.builder()
                .email("consumer")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_CONSUMER)
                .build();
        userRepository.save(consumer);

        User doctor = User.builder()
                .email("doctor")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_DOCTOR)
                .build();
        userRepository.save(doctor);
    }

}
