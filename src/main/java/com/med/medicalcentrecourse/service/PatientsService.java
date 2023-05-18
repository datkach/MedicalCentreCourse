package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Patient;

import java.util.List;

public interface PatientsService {
    Patient create(Patient patient);
    Patient getPatientByID(Integer id);
    List<Patient> getAllPatients();
    List<Patient> getALlOrderedAsc();
    List<Patient> getAllPatientsByDoctor(String surname);
    Patient updateById(Integer id, Patient patient);
    void removeById(Integer id);
    void removeAll();
}
