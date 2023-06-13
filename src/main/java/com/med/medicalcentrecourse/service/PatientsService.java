package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientsService {
    Patient create(Patient patient);
    Patient getPatientByID(Integer id);
    List<Patient> getAllPatients();
    List<Patient> getALlOrderedAsc();
    List<Patient> getAllPatientsByDoctor(String surname);
    List<Patient> getAllPatientsByFirstName(String name);
    List<Patient> getAllPatientByLastName(String surname);
    List<Patient> getAllPatientByPhoneNumber(String phoneNumber);

    Patient updateById(Integer id, Patient patient);
    void removeById(Integer id);
    void removeAll();
     boolean checkDate(LocalDate date);
    boolean checkDoctorSurname(String patient);
}
