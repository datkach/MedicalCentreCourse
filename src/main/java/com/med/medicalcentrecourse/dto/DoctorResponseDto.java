package com.med.medicalcentrecourse.dto;

import com.med.medicalcentrecourse.model.enums.Specialization;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class DoctorResponseDto {
    @NotNull(message = "Name must be not null")
    @Size(min = 2,max = 20,message = "Name must be by 2 to 20 symbol")
    public String name;
    @NotNull(message = "Surname must be not null")
    @Size(min = 2,max = 20,message = "Surname must be by 2 to 20 symbol")
    public String surname;
    @NotNull(message = "BirthdayDate")
    public LocalDate birthdayDate;
    @NotNull(message = "specialization must be not null")
    public Specialization specialization;
}
