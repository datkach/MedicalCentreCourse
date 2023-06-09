package com.med.medicalcentrecourse.repository;

import com.med.medicalcentrecourse.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findAllByDoctorSurname(String doctorName);
    List<Patient> findAllByOrderByLastNameAsc();
    List<Patient> findAllByLastName(String lastName);
    List<Patient> findAllByFirstName(String firstName);
    List<Patient> findAllByPhoneNumber(String number);
}
