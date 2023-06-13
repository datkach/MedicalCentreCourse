package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.repository.DoctorRepository;
import com.med.medicalcentrecourse.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceBean implements DoctorService {
private final DoctorRepository doctorRepository;
    public Doctor create(Doctor doctor) {
       return doctorRepository.save(doctor);
    }
    public Doctor getDoctorByID(Integer id){
        return doctorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
    public List<Doctor> getALlDoctors(){
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getAllDoctorsByName(String name) {
        return doctorRepository.findAllByName(name);
    }

    @Override
    public List<Doctor> getAllDoctorsBySurname(String surname) {
        return doctorRepository.findAllBySurname(surname);
    }

    @Override
    public List<Doctor> getAllDoctorsBySpecialization(Specialization specialization) {
        return doctorRepository.findAllBySpecialization(specialization);
    }
//Костыль(
    @Override
    public Doctor getAllDoctorsByNameBySurnameBySpecialization(String name, String surname, Specialization specialization) {
        return doctorRepository.findByNameIgnoreCaseAndSurnameIgnoreCaseAndSpecialization(name,surname,specialization);
    }

    public Doctor updateById(Integer id, Doctor doctor) {
        return doctorRepository.findById(id)
                .map(entity -> {
                    entity.setName(doctor.getName());
                    entity.setSurname(doctor.getSurname());
                    entity.setSpecialization(doctor.getSpecialization());
                    entity.setBirthdayDate(
                            checkDate(doctor.getBirthdayDate())
                            ? entity.getBirthdayDate()
                            : doctor.getBirthdayDate());
                    return doctorRepository.save(entity);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
    public void removeById(Integer id) {
        doctorRepository.deleteById(id);
    }
    public void removeAll() {
        doctorRepository.deleteAll();
    }
    public boolean checkDate(LocalDate date){
        return date.isAfter(LocalDate.now())
                || date.getYear() < LocalDate.now().minusYears(150).getYear();
    }
}
