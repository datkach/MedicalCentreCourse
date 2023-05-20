package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.DoctorSearchForm;
import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.model.PatientSearchForm;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.service.PatientsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@AllArgsConstructor
public class PatientWebController {
    private final PatientsService patientsService;
    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientsService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient/patients";
    }
    // получаем доктора по id
    @GetMapping("/patients/{id}")
    public String getPatientById(@PathVariable Integer id, Model model) {
        Patient patient = patientsService.getPatientByID(id);
        model.addAttribute("patient", patient);
        return "patient/patient-details";
    }
    //пошук по фамилии
    @GetMapping("/patients/searchBySurname")
    public String searchPatientsBySurname(Model model) {
        model.addAttribute("searchForm", new PatientSearchForm());
        return "patient/patient-search-surname";
    }

    @PostMapping("/patients/searchBySurname")
    public String processPatientsSearchBySurname(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String surname = searchForm.getLastName();
        List<Patient> patients = patientsService.getAllPatientByLastName(surname);
        model.addAttribute("patients", patients);
        return "patient/patient-search-results";
    }
    //пошук по имени
    @GetMapping("/patients/searchByName")
    public String searchPatientsByName(Model model) {
        model.addAttribute("searchForm", new PatientSearchForm());
        return "patient/patient-search-name";
    }

    @PostMapping("/patients/searchByName")
    public String processDoctorSearchByName(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String name = searchForm.getFirstName();
        List<Patient> patients = patientsService.getAllPatientsByFirstName(name);
        model.addAttribute("patients", patients);
        return "patient/patient-search-results";
    }
    // пошук по специализации
    @GetMapping("patients/searchByPhoneNumber")
    public String searchPatientsByPhoneNumber(Model model) {
        model.addAttribute("searchForm" , new PatientSearchForm());
        return "patient/patient-search-phone";
    }
    @PostMapping("/patients/searchByPhoneNumber")
    public String processPatientsByPhoneNumber(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model){
        String phoneNumber = searchForm.getPhoneNumber();
        List<Patient> patients = patientsService.getAllPatientByPhoneNumber(phoneNumber);
        model.addAttribute("patients",patients);
        return "patient/patient-search-results";
    }
    //форма создания и её обработка
    @GetMapping("/patients/create")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/patient-form";
    }
    @PostMapping("/patients/create")
    public String processCreatePatientForm(@ModelAttribute("doctor") Patient patient) {
        patientsService.create(patient);
        return "redirect:/patients";
    }
    //форма обновления и её оброботка
//    @GetMapping("/patients/update/{id}")
//    public String showUpdateDoctorForm(@PathVariable Integer id, Model model) {
//        Doctor doctor = doctorService.getDoctorByID(id);
//        model.addAttribute("doctor", doctor);
//        return "doctor/doctor-update";
//    }
//    @PostMapping("/doctors/update/{id}")
//    public String processUpdateDoctorForm(@PathVariable Integer id, @ModelAttribute("doctor") Doctor doctor) {
//        doctorService.updateById(id, doctor);
//        return "redirect:/doctors";
//    }
//    // вилучення доктора по id
//    @GetMapping("/doctors/delete/{id}")
//    public String deleteDoctor(@PathVariable Integer id) {
//        doctorService.removeById(id);
//        return "redirect:/doctors";
//    }
    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient saveEmployee(@RequestBody @Valid Patient patient) {
        return patientsService.create(patient);
    }
    @PutMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient refreshDoctor(@PathVariable("id") Integer id, @RequestBody Patient patient){
        return patientsService.updateById(id,patient);
    }
    //Получение списка юзеров
    @GetMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllUsers() {
        return patientsService.getAllPatients();
    }
    @GetMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getDoctorById(@PathVariable("id") Integer id){
        return patientsService.getPatientByID(id);
    }
    @GetMapping("/patientsByDoctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatientByDoctor(@RequestParam("surname") String surname){
        return patientsService.getAllPatientsByDoctor(surname);
    }
    @GetMapping("/patientsOrderedAsc")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatientOrderedAsc(){
        return patientsService.getALlOrderedAsc();
    }

    //Удаление по id
    @PatchMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        patientsService.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/patient")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        patientsService.removeAll();
    }
}
