package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.model.PatientSearchForm;
import com.med.medicalcentrecourse.service.DoctorService;
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
    public static final String PATIENTS = "patients";
    public static final String SEARCH_FORM = "searchForm";
    public static final String PATIENT_SEARCH_RESULTS = "patient-search-results";
    public static final String REDIRECT_PATIENTS = "redirect:/patients";
    private final PatientsService patientsService;
    private final DoctorService doctorService;
    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientsService.getAllPatients();
        model.addAttribute(PATIENTS, patients);
        return PATIENTS;
    }
    @GetMapping("/patients/ordered")
    public String getAllPatientsOrdered(Model model) {
        List<Patient> patients = patientsService.getALlOrderedAsc();
        model.addAttribute(PATIENTS, patients);
        return PATIENTS;
    }
    // получаем доктора по id
    @GetMapping("/patients/{id}")
    public String getPatientById(@PathVariable Integer id, Model model) {
        Patient patient = patientsService.getPatientByID(id);
        model.addAttribute(PATIENTS, patient);
        return "patient-details";
    }
    //пошук по фамилии
    @GetMapping("/patients/searchBySurname")
    public String searchPatientsBySurname(Model model) {
        model.addAttribute(SEARCH_FORM, new PatientSearchForm());
        return "patient-search-surname";
    }

    @PostMapping("/patients/searchBySurname")
    public String processPatientsSearchBySurname(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String surname = searchForm.getLastName();
        List<Patient> patients = patientsService.getAllPatientByLastName(surname);
        model.addAttribute(PATIENTS, patients);
        return PATIENT_SEARCH_RESULTS;
    }
    //пошук по имени
    @GetMapping("/patients/searchByName")
    public String searchPatientsByName(Model model) {
        model.addAttribute(SEARCH_FORM, new PatientSearchForm());
        return "patient-search-name";
    }

    @PostMapping("/patients/searchByName")
    public String processDoctorSearchByName(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model) {
        String name = searchForm.getFirstName();
        List<Patient> patients = patientsService.getAllPatientsByFirstName(name);
        model.addAttribute(PATIENTS, patients);
        return PATIENT_SEARCH_RESULTS;
    }
    // пошук по специализации
    @GetMapping("patients/searchByPhoneNumber")
    public String searchPatientsByPhoneNumber(Model model) {
        model.addAttribute(SEARCH_FORM, new PatientSearchForm());
        return "patient-search-phone";
    }
    @PostMapping("/patients/searchByPhoneNumber")
    public String processPatientsByPhoneNumber(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model){
        String phoneNumber = searchForm.getPhoneNumber();
        List<Patient> patients = patientsService.getAllPatientByPhoneNumber(phoneNumber);
        model.addAttribute(PATIENTS,patients);
        return PATIENT_SEARCH_RESULTS;
    }
    @GetMapping("patients/searchPatientsByDoctor")
    public String searchDoctorByPatientCart(Model model) {
        model.addAttribute(SEARCH_FORM , new PatientSearchForm());
        return "patient-search-doctor";
    }
    @PostMapping("/patients/searchPatientsByDoctor")
    public String processDoctorByPatientCart(@ModelAttribute("searchForm") PatientSearchForm searchForm, Model model){
        String doctorSurname = searchForm.getDoctorSurname();
        List<Patient> patients;
        if (!doctorService.getAllDoctorsBySurname(doctorSurname).isEmpty()){
        patients = patientsService.getAllPatientsByDoctor(doctorSurname);
        model.addAttribute(PATIENTS,patients);}
        return PATIENT_SEARCH_RESULTS;
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
        if(patientsService.checkDoctorSurname(patient.getDoctorSurname()))
            return "error-doctor-surname-by-patient";
        patientsService.create(patient);
        return REDIRECT_PATIENTS;
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
        if(patientsService.checkDoctorSurname(patient.getDoctorSurname()))
            return "error-doctor-surname-by-patient";
        patientsService.updateById(id, patient);
        return REDIRECT_PATIENTS;
    }
    // вилучення пациента по id
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        patientsService.removeById(id);
        return REDIRECT_PATIENTS;
    }
}
