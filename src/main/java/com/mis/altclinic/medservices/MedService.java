package com.mis.altclinic.medservices;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Length(max = 1000)
    private String description;
    @PositiveOrZero
    private Double price;

    public MedService(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
