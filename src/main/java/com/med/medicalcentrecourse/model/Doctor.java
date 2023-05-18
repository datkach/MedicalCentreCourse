package com.med.medicalcentrecourse.model;

import com.med.medicalcentrecourse.model.enums.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdayDate;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
}
