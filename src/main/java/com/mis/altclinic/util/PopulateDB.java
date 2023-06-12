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

        MedService ms1 = new MedService("Кардіолог", "Кардіолог - це лікар, спеціалізуючийся на діагностиці, лікуванні та профілактиці захворювань серця і судин.", 100.0);
        MedService ms2 = new MedService("Дерматолог", "Дерматолог - це лікар, який спеціалізується на діагностиці, лікуванні та профілактиці захворювань шкіри, волосся та нігтьової пластинки.", 100.0);
        MedService ms3 = new MedService("Ендокринолог", "Ендокринолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями ендокринної системи.", 100.0);
        MedService ms4 = new MedService("Гастроентеролог", "Гастроентеролог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями шлунково-кишкового тракту. ", 100.0);
        MedService ms5 = new MedService("Гематолог", "\n" +
                "Гематолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями крові та кровотворних органів. ", 100.);
        MedService ms6 = new MedService("Травматолог", "\n" +
                "Травматолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні травмами та захворюваннями, пов'язаними з опорно-руховою системою.", 100.0);
        MedService ms7 = new MedService("Нефролог", "\n" +
                "Нефролог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями нирок.", 100.0);
        MedService ms8 = new MedService("Невролог", "\n" +
                "Невролог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями нервової системи.", 100.0);
        MedService ms9 = new MedService("Онколог", " це лікар, спеціалізований у діагностиці, лікуванні та управлінні онкологічними захворюваннями, відомими також як рак", 100.0);
        MedService ms10 = new MedService("Пульмонолог", "Пульмонолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями дихальної системи, зокрема легенів. ", 100.0);
        MedService ms11 = new MedService("Ревматолог", "Ревматолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні ревматологічними захворюваннями.", 100.0);
        MedService ms12 = new MedService("Отоларинголог", "Отоларинголог це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями вуха, горла і носа.", 100.0);
        MedService ms13 = new MedService("Хірург", "Хірург - це лікар, спеціалізований у хірургічному лікуванні хвороб і травм, що потребують хірургічного втручання.", 100.0);
        MedService ms14 = new MedService("Гінеколог", "Гінеколог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями жіночої репродуктивної системи.", 100.0);
        MedService ms15 = new MedService("Уролог", "Уролог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями сечової системи у чоловіків та жінок, а також статевих органів чоловіків.", 100.0);
        MedService ms16 = new MedService("Офтальмолог", "Офтальмолог - це лікар, спеціалізований у діагностиці, лікуванні та управлінні захворюваннями очей і органу зору.", 100.0);

        List<MedService> medServices = List.of(ms1, ms2, ms3, ms4, ms5, ms6, ms7, ms8, ms9, ms10, ms11, ms12, ms13, ms14, ms15, ms16);
        medServiceRepository.saveAll(medServices);

        Doctor d1 = new Doctor("Олександр","Степаненко","Doctor1@mail.com",  "password","Михайлович", "+38103018031",35,"Кардіолог","НЛМУ ім. Данила Галицького", "10", List.of(ms1));
        Doctor d2 = new Doctor("Ірина","Петренко","Petrenko@mail.com",  "password","Володимирівна", "+38103018032",30,"Дерматолог","НЛМУ ім. Данила Галицького", "7", List.of(ms2));
        Doctor d3 = new Doctor("Василь","Коваленко","Kovalenko3@mail.com",  "password","Іванович", "+38103018033",43,"Ендокринолог","НЛМУ ім. Данила Галицького", "20", List.of(ms3));
        Doctor d4 = new Doctor("Юлія","Шевченко","Shevchenko@mail.com",  "password","Олегівна", "+38103018034",47,"Гастроентеролог","НЛМУ ім. Данила Галицького", "21", List.of(ms4));
        Doctor d5 = new Doctor("Максим","Ковальчук","Kovalchuk@mail.com",  "password","Сергійович", "+38103018035",26,"Гематолог","НЛМУ ім. Данила Галицького", "3", List.of(ms5));
        Doctor d6 = new Doctor("Наталія","Мельник","Melnyk@mail.com",  "password","Петрівна", "+38103018036",34,"Інфекціоніст","НЛМУ ім. Данила Галицького", "11", List.of(ms8));
        Doctor d7 = new Doctor("Андрій","Лисенко","Lysenko@mail.com",  "password","Віталійович", "+38103018037",55,"Нефролог","НЛМУ ім. Данила Галицького", "30", List.of(ms7));
        Doctor d8 = new Doctor("Людмила","Іванова","Ivanova@mail.com",  "password","Миколаївна", "+38103018037",31,"Травматолог","НЛМУ ім. Данила Галицького", "10", List.of(ms7));
        Doctor d9 = new Doctor("Олег","Бондаренко ","Bondarenko@mail.com",  "password","Ігорович", "+38103018037",43,"Гінеколог","НЛМУ ім. Данила Галицького", "18", List.of(ms7));
        Doctor d10 = new Doctor("Оксана","Кравченко","Kravchenko@mail.com",  "password","Сергіївна", "+38103018037",71,"Невролог","НЛМУ ім. Данила Галицького", "46", List.of(ms7));
        Doctor d11= new Doctor("Марія","Левченко","Levchenko@mail.com",  "password","Василівна", "+38103018037",62,"Онколог","НЛМУ ім. Данила Галицького", "39", List.of(ms7));
        Doctor d12 = new Doctor("Ігор","Григоренко ","Hryhorenko@mail.com",  "password","Олександрович", "+38103018037",39,"Пульмонолог","НЛМУ ім. Данила Галицького", "16", List.of(ms7));
        Doctor d13 = new Doctor("Анастасія","Сидоренко","Sydorenko@mail.com",  "password","Юріївна", "+38103018037",29,"Ревматолог","НЛМУ ім. Данила Галицького", "5", List.of(ms7));
        Doctor d14 = new Doctor("Дмитро ","Романенко","Romanenko@mail.com",  "password","Володимирович", "+38103018037",24,"Отоларинголог","НЛМУ ім. Данила Галицького", "1", List.of(ms7));
        Doctor d15 = new Doctor("Катерина","Марченко ","Marchenko@mail.com",  "password","Анатоліївна", "+38103018037",30,"Хірург","НЛМУ ім. Данила Галицького", "9", List.of(ms7));
        Doctor d16 = new Doctor("Володимир","Коваленко","Kovalenko@mail.com",  "password","Вікторович", "+38103018037",51,"Уролог","НЛМУ ім. Данила Галицького", "32", List.of(ms7));
        Doctor d17 = new Doctor("Ірина ","Гончаренко","Honcharenko@mail.com",  "password","Олександрівна", "+38103018037",40,"Офтальмолог","НЛМУ ім. Данила Галицького", "12", List.of(ms7));


        List<Doctor> doctors = List.of(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17);
        doctorService.saveAll2(doctors);

        Consumer c1 = new Consumer("Олексій","Савчук","Savchuk@mail.com","password","Ігорович","+38108030","ConsumerAddress1",21,"1+");
        Consumer c2 = new Consumer("Надія","Жукова ","Zhukova@mail.com","password","Володимирівна","+38108030","ConsumerAddress2",22,"2-");
        Consumer c3 = new Consumer("Василь ","Марченко ","Marchenko1@mail.com","password","Олександрович","+38108030","ConsumerAddress3",23,"3-");
        Consumer c4 = new Consumer("Юлія ","Ткаченко ","Tkachenko@mail.com","password","Сергіївна","+38108030","ConsumerAddress4",24,"4+");
        Consumer c5 = new Consumer("Максим ","Біленька ","Bilenka @mail.com","password","Миколайович","+38108030","ConsumerAddress5",25,"1+");
        Consumer c6 = new Consumer("Анна","Гриценко","Hrytsenko@mail.com","password","Іванівна","+38108030","ConsumerAddress6",26,"2-");
        Consumer c7 = new Consumer("Олег","Попович ","Consumer7@mail.com","password","Петрович","+38108030","ConsumerAddress7",27,"3-");
        Consumer c8 = new Consumer("Марина ","Остапенко ","Ostapenko @mail.com","password","Євгеніївна","+38108030","ConsumerAddress8",28,"4+");
        Consumer c9 = new Consumer("Денис ","Шульга ","Consumer9@mail.com","password","Вікторович","+38108030","ConsumerAddress9",29,"1+");

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