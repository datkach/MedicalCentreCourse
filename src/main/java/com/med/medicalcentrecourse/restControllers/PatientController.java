package com.med.medicalcentrecourse.restControllers;

import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.service.PatientsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {
//    private final PatientsService patientsService;
//    @PostMapping("/patient")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Patient saveEmployee(@RequestBody @Valid Patient patient) {
//        return patientsService.create(patient);
//    }
//    @PutMapping("/patient/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Patient refreshDoctor(@PathVariable("id") Integer id, @RequestBody Patient patient){
//        return patientsService.updateById(id,patient);
//    }
//    //Получение списка юзеров
//    @GetMapping("/patient")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Patient> getAllUsers() {
//        return patientsService.getAllPatients();
//    }
//    @GetMapping("/patient/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Patient getDoctorById(@PathVariable("id") Integer id){
//        return patientsService.getPatientByID(id);
//    }
//    @GetMapping("/patientsByDoctor")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Patient> getAllPatientByDoctor(@RequestParam("surname") String surname){
//        return patientsService.getAllPatientsByDoctor(surname);
//    }
//    @GetMapping("/patientsOrderedAsc")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Patient> getAllPatientOrderedAsc(){
//        return patientsService.getALlOrderedAsc();
//    }
//
//    //Удаление по id
//    @PatchMapping("/patient/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void removeEmployeeById(@PathVariable Integer id) {
//        patientsService.removeById(id);
//    }
//
//    //Удаление всех юзеров
//    @DeleteMapping("/patient")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void removeAllUsers() {
//        patientsService.removeAll();
//    }
}
