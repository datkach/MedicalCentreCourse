package com.med.medicalcentrecourse.repository;

import com.med.medicalcentrecourse.model.Appointment;
import com.med.medicalcentrecourse.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findAllByPatients(Patient patient);
}
