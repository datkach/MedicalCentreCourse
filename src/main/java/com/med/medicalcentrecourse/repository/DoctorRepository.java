package com.med.medicalcentrecourse.repository;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.enums.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> findAllByName(String name);
    List<Doctor> findAllBySurname(String name);
    Doctor findByNameIgnoreCaseAndSurnameIgnoreCaseAndSpecialization(String name, String surname, Specialization specialization);
    List<Doctor> findAllBySpecialization(Specialization specialization);
}
