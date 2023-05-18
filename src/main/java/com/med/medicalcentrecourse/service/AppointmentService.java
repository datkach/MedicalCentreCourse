package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Appointment;

import java.util.List;
import java.util.Set;

public interface AppointmentService {
    Appointment create(Appointment appointment);
    Appointment getAppointmentByID(Integer id);
    List<Appointment> getAppointmentByPersonID(Integer id);
    List<Appointment> getALlAppointment();
    Appointment updateById(Integer id,Appointment appointment);
     void removeById(Integer id);
     void removeAll();
    Set<String> getAllDiagnoseByPatientID(Integer id);
    List<Appointment> getAllAppointmentBeforeNow();
    }
