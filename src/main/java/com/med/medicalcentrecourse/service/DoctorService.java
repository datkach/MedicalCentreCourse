package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.enums.Specialization;

import java.util.List;

public interface DoctorService {
    Doctor create(Doctor doctor);
    Doctor getDoctorByID(Integer id);
    List<Doctor> getALlDoctors();
    List<Doctor> getAllDoctorsByName(String name);
    List<Doctor> getAllDoctorsBySurname(String surname);
    List<Doctor> getAllDoctorsBySpecialization(Specialization specialization);
    Doctor getAllDoctorsByNameBySurnameBySpecialization(String name, String surname, Specialization specialization);
    Doctor updateById(Integer id, Doctor doctor);
    void removeById(Integer id);
    void removeAll();
}
