package com.med.medicalcentrecourse.model;

import com.med.medicalcentrecourse.model.enums.Specialization;

public class DoctorSearchForm {
    private String name;
    private String surname;
    private Specialization specialization;

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}

