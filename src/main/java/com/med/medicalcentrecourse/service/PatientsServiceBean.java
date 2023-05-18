package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.repository.PatientsRepository;
import com.med.medicalcentrecourse.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientsServiceBean implements PatientsService{
    private final PatientsRepository patientsRepository;
    private final DoctorService doctorService;
    @Override
    public Patient create(Patient patient) {
        if(checkDate(patient.getBirthDate()))
            throw new NullPointerException("Not true Date");
        return patientsRepository.save(patient);
    }
    @Override
    public Patient getPatientByID(Integer id){
        return patientsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
    @Override
    public List<Patient> getAllPatients(){
        return patientsRepository.findAll();
    }

    @Override
    public List<Patient> getALlOrderedAsc() {
        return patientsRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public List<Patient> getAllPatientsByDoctor(String surname) {
        Doctor doctor = doctorService.getAllDoctorsBySurname(surname).stream()
                .filter(e -> e.getSpecialization().equals(Specialization.FamilyDoctor))
                .findFirst().orElseThrow(ResourceNotFoundException:: new);
        return patientsRepository.findAllByDoctorSurname(doctor.getSurname());
    }

    @Override
    public Patient updateById(Integer id, Patient patient) {
        return patientsRepository.findById(id)
                .map(entity -> {
                    entity.setFirstName(patient.getFirstName());
                    entity.setLastName(patient.getLastName());
                    entity.setGender(patient.getGender());
                    entity.setBirthDate(
                            checkDate(patient.getBirthDate())
                                    ? entity.getBirthDate()
                                    : patient.getBirthDate());
                    entity.setAddress(patient.getAddress());
                    entity.setBloodGroup(patient.getBloodGroup());
                    entity.setPhoneNumber(patient.getPhoneNumber());
                    return patientsRepository.save(entity);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
    @Override
    public void removeById(Integer id) {
        patientsRepository.deleteById(id);
    }
    @Override
    public void removeAll() {
        patientsRepository.deleteAll();
    }
    private boolean checkDate(LocalDate date){
        return date.isAfter(LocalDate.now())
                || date.getYear() < LocalDate.now().minusYears(150).getYear();
    }
}
