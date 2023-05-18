package com.med.medicalcentrecourse.model;

import java.time.LocalDate;

import com.med.medicalcentrecourse.model.enums.Gender;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private String bloodGroup;

    @NotNull
    private LocalDate birthDate;
    private String doctorSurname;

}