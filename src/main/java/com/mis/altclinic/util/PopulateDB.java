package com.mis.altclinic.util;

import com.mis.altclinic.consumers.Consumer;
import com.mis.altclinic.consumers.ConsumerService;
import com.mis.altclinic.doctor_appointments.DoctorAppointment;
import com.mis.altclinic.doctor_appointments.DoctorAppointmentService;
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

        Doctor d1 = new Doctor("DoctorFirstName1","DoctorLastName1","Doctor1@mail.com",  "password","DoctorPatronymic1", "+38103018031",21,"Cardiology","Cardiology university", "Several years", List.of(ms1));
        Doctor d2 = new Doctor("DoctorFirstName2","DoctorLastName2","Doctor2@mail.com",  "password","DoctorPatronymic2", "+38103018032",22,"Dermatology","Dermatology university", "Several years", List.of(ms2));
        Doctor d3 = new Doctor("DoctorFirstName3","DoctorLastName3","Doctor3@mail.com",  "password","DoctorPatronymic3", "+38103018033",23,"Endocrinology","Endocrinoly university", "Several years", List.of(ms3));
        Doctor d4 = new Doctor("DoctorFirstName4","DoctorLastName4","Doctor4@mail.com",  "password","DoctorPatronymic4", "+38103018034",24,"Gastroenteritis","Gastroentey university", "Several years", List.of(ms4));
        Doctor d5 = new Doctor("DoctorFirstName5","DoctorLastName5","Doctor5@mail.com",  "password","DoctorPatronymic5", "+38103018035",25,"Hematology","Hematology university", "Several years", List.of(ms5));
        Doctor d6 = new Doctor("DoctorFirstName6","DoctorLastName6","Doctor6@mail.com",  "password","DoctorPatronymic6", "+38103018036",26,"Infectious","Infectious university", "Several years", List.of(ms6));
        Doctor d7 = new Doctor("DoctorFirstName7","DoctorLastName7","Doctor7@mail.com",  "password","DoctorPatronymic7", "+38103018037",27,"Nephrology","Nephrology university", "Several years", List.of(ms7));

        List<Doctor> doctors = List.of(d1,d2,d3,d4,d5,d6,d7);
        doctorService.saveAll2(doctors);

        Consumer c1 = new Consumer("ConsumerFirstName1","ConsumerLastName1","Consumer1@mail.com","password","ConsumerPatronymic1","+38108030","ConsumerAddress1",21,"1+");
        Consumer c2 = new Consumer("ConsumerFirstName2","ConsumerLastName2","Consumer2@mail.com","password","ConsumerPatronymic2","+38108030","ConsumerAddress2",22,"2-");
        Consumer c3 = new Consumer("ConsumerFirstName3","ConsumerLastName3","Consumer3@mail.com","password","ConsumerPatronymic3","+38108030","ConsumerAddress3",23,"3-");
        Consumer c4 = new Consumer("ConsumerFirstName4","ConsumerLastName4","Consumer4@mail.com","password","ConsumerPatronymic4","+38108030","ConsumerAddress4",24,"4+");
        Consumer c5 = new Consumer("ConsumerFirstName5","ConsumerLastName5","Consumer5@mail.com","password","ConsumerPatronymic5","+38108030","ConsumerAddress5",25,"1+");
        Consumer c6 = new Consumer("ConsumerFirstName6","ConsumerLastName6","Consumer6@mail.com","password","ConsumerPatronymic6","+38108030","ConsumerAddress6",26,"2-");
        Consumer c7 = new Consumer("ConsumerFirstName7","ConsumerLastName7","Consumer7@mail.com","password","ConsumerPatronymic7","+38108030","ConsumerAddress7",27,"3-");
        Consumer c8 = new Consumer("ConsumerFirstName8","ConsumerLastName8","Consumer8@mail.com","password","ConsumerPatronymic8","+38108030","ConsumerAddress8",28,"4+");
        Consumer c9 = new Consumer("ConsumerFirstName9","ConsumerLastName9","Consumer9@mail.com","password","ConsumerPatronymic9","+38108030","ConsumerAddress9",29,"1+");

        List<Consumer> consumers = List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9);
        consumerService.saveAll(consumers);

        DoctorAppointment da1 = new DoctorAppointment(c1, d1, LocalDateTime.now().plusDays(2).minusHours(10), "Some problem", 100.0);
        DoctorAppointment da2 = new DoctorAppointment(c2, d2, LocalDateTime.now().plusDays(1).minusHours(10), "Some problem", 200.0);
        DoctorAppointment da3 = new DoctorAppointment(c3, d3, LocalDateTime.now().plusDays(3).minusHours(10), "Some problem", 300.0);
        DoctorAppointment da4 = new DoctorAppointment(c4, d4, LocalDateTime.now().plusDays(4).minusHours(10), "Some problem", 150.0);
        DoctorAppointment da5 = new DoctorAppointment(c5, d5, LocalDateTime.now().plusDays(5).minusHours(10), "Some problem", 100.0);
        DoctorAppointment da6 = new DoctorAppointment(c6, d5, LocalDateTime.now().plusDays(6).minusHours(10), "Some problem", 170.0);
        DoctorAppointment da7 = new DoctorAppointment(c7, d1, LocalDateTime.now().plusDays(7).minusHours(10), "Some problem", 100.0);
        DoctorAppointment da8 = new DoctorAppointment(c1, d6, LocalDateTime.now().plusDays(8).minusHours(10), "Some problem", 100.0);
        DoctorAppointment da9 = new DoctorAppointment(c1, d7, LocalDateTime.now().plusDays(1).minusHours(10), "Some problem", 180.0);
        DoctorAppointment da10 = new DoctorAppointment(c2, d6, LocalDateTime.now().plusDays(2).minusHours(10), "Some problem", 1000.0);
        DoctorAppointment da11 = new DoctorAppointment(c2, d7, LocalDateTime.now().minusDays(10).minusHours(10), "Some problem", 10000.0);

        List<DoctorAppointment> doctorAppointments = List.of(da1,da2,da3,da4,da5,da6,da7,da8,da9,da10,da11);
        doctorAppointmentService.saveAll(doctorAppointments);

        User admin = User.builder()
                .email("admin")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(admin);

    }

}
