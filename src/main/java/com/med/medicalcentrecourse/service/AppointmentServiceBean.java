package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Appointment;
import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.repository.AppointmentRepository;
import com.med.medicalcentrecourse.repository.PatientsRepository;
import com.med.medicalcentrecourse.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppointmentServiceBean implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientsRepository patientsRepository;
    public Appointment create(Appointment appointment) {
        if(checkDate(appointment.getActionTime()))
            throw new NullPointerException("Not true Date");
        return appointmentRepository.save(appointment);
    }
    public Appointment getAppointmentByID(Integer id){
        return appointmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
    public List<Appointment> getAppointmentByPersonID(Integer id){
        Patient patient1 = patientsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
   return  appointmentRepository.findAllByPatients(patient1);}
    public List<Appointment> getALlAppointment(){
        return appointmentRepository.findAll();
    }
    public Appointment updateById(Integer id,Appointment appointment) {
        return appointmentRepository.findById(id)
                .map(entity -> {
                    entity.setDoctors(appointment.getDoctors());
                    entity.setPatients(appointment.getPatients());
                    entity.setActionTime(
                            checkDate(appointment.getActionTime())
                                    ? entity.getActionTime()
                                    : appointment.getActionTime());
                    entity.setDescription(appointment.getDescription());
                    entity.setTreatment(appointment.getTreatment());
                    return appointmentRepository.save(entity);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
    public void removeById(Integer id) {
        appointmentRepository.deleteById(id);
    }
    public void removeAll() {
        appointmentRepository.deleteAll();
    }

    public Set<String> getAllDiagnoseByPatientID(Integer id){
      List<Appointment> list= getAppointmentByPersonID(id);
      Set<String> stringSet = new HashSet<>();
        for (Appointment l:
             list) {
            stringSet.add(l.getDescription());
        }
        return stringSet;
    }
    public List<Appointment> getAllAppointmentBeforeNow() {
        return appointmentRepository.findAppointmentsByActionTimeBefore(LocalDateTime.now());
    }

    private boolean checkDate(LocalDateTime date){
        return date.getYear() < LocalDateTime.now().minusYears(100).getYear()
                || date.getYear() > LocalDateTime.now().plusYears(30).getYear();
    }
}
