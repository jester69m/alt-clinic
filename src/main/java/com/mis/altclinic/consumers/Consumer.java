package com.mis.altclinic.consumers;

import com.mis.altclinic.users.Role;
import com.mis.altclinic.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Consumer extends User {

    private String patronymic;

    @Column(length = 13)
    private String phone_number;

    private String address;

    @Positive
    private Integer age;

    @Column(length = 3)
    private String blood_type;



    public Consumer(String first_name,
                    String last_name,
                    String email,
                    String password,
                    String patronymic,
                    String phone_number,
                    String address,
                    int age,
                    String blood_type) {

        super(first_name,last_name, email, password, Role.ROLE_CONSUMER, true);
        this.patronymic = patronymic;
        this.phone_number = phone_number;
        this.address = address;
        this.age = age;
        this.blood_type = blood_type;
    }

}
