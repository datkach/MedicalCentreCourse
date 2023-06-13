package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.model.PatientSearchForm;
import com.med.medicalcentrecourse.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@AllArgsConstructor
public class PatientWebController {
    private final PatientsService patientsService;
    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientsService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }
    // получаем доктора по id
    @GetMapping("/patients/{id}")
    public String getPatientById(@PathVariable Integer id, Model model) {
        Patient patient = patientsService.getPatientByID(id);
        model.addAttribute("patient", patient);
        return "patient-details";
    }
    //пошук по фамилии
    @GetMapping("/patients/searchBySurname")
    public String searchPatientsBySurname(Model model) {
        model.addAttribute("searchForm", new PatientSearchForm());
        return "patient-search-surname";
    }

    @PostMapping("/patients/searchBySurname")
    public String processPatientsSearchBySurname(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String surname = searchForm.getLastName();
        List<Patient> patients = patientsService.getAllPatientByLastName(surname);
        model.addAttribute("patients", patients);
        return "patient-search-results";
    }
    //пошук по имени
    @GetMapping("/patients/searchByName")
    public String searchPatientsByName(Model model) {
        model.addAttribute("searchForm", new PatientSearchForm());
        return "patient-search-name";
    }

    @PostMapping("/patients/searchByName")
    public String processDoctorSearchByName(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String name = searchForm.getFirstName();
        List<Patient> patients = patientsService.getAllPatientsByFirstName(name);
        model.addAttribute("patients", patients);
        return "patient-search-results";
    }
    // пошук по специализации
    @GetMapping("patients/searchByPhoneNumber")
    public String searchPatientsByPhoneNumber(Model model) {
        model.addAttribute("searchForm" , new PatientSearchForm());
        return "patient-search-phone";
    }
    @PostMapping("/patients/searchByPhoneNumber")
    public String processPatientsByPhoneNumber(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model){
        String phoneNumber = searchForm.getPhoneNumber();
        List<Patient> patients = patientsService.getAllPatientByPhoneNumber(phoneNumber);
        model.addAttribute("patients",patients);
        return "patient-search-results";
    }
    @GetMapping("patients/searchPatientsByDoctor")
    public String searchDoctorByPatientCart(Model model) {
        model.addAttribute("searchForm" , new PatientSearchForm());
        return "patient-search-doctor";
    }
    @PostMapping("/patients/searchPatientsByDoctor")
    public String processDoctorByPatientCart(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model){
        String doctorSurname = searchForm.getDoctorSurname();
        List<Patient> patients = patientsService.getAllPatientsByDoctor(doctorSurname);
        model.addAttribute("patients",patients);
        return "patient-search-results";
    }
    //форма создания и её обработка
    @GetMapping("/patients/create")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }
    @PostMapping("/patients/create")
    public String processCreatePatientForm(@ModelAttribute("patient") Patient patient) {
        if(patientsService.checkDate(patient.getBirthDate()))
            return "error-date-patient";
        patientsService.create(patient);
        return "redirect:/patients";
    }
    @GetMapping("/patients/update/{id}")
    public String showUpdatePatientsForm(@PathVariable Integer id, Model model) {
        Patient patient = patientsService.getPatientByID(id);
        model.addAttribute("patient", patient);
        return "patient-update";
    }
    @PostMapping("/patients/update/{id}")
    public String processUpdatePatientForm(@PathVariable Integer id, @ModelAttribute("patient") Patient patient) {
        if(patientsService.checkDate(patient.getBirthDate()))
            return "error-date-patient";
        patientsService.updateById(id, patient);
        return "redirect:/patients";
    }
    // вилучення пациента по id
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        patientsService.removeById(id);
        return "redirect:/patients";
    }
}
