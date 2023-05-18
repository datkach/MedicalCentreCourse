package com.med.medicalcentrecourse.repository;

import com.med.medicalcentrecourse.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Integer> {
//    List<Diagnosis> findAllBy();
}
