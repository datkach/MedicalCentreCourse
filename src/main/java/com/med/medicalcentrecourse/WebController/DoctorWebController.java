package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.DoctorSearchForm;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class DoctorWebController {
    private final DoctorService doctorService;
    private static final String DOCTORS = "doctors";
    private static final String DOCTOR = "doctor";
    private static final String SEARCH_FORM = "searchForm";
    private static final String DOCTOR_SEARCH = "doctor-search-results";
    private static final String REDIRECT_DOCTOR = "redirect:/doctors";
// получаем всех докторов
    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getALlDoctors();
        model.addAttribute(DOCTORS, doctors);
        return DOCTORS;
    }
// получаем доктора по id
    @GetMapping("/doctors/{id}")
    public String getDoctorById(@PathVariable Integer id, Model model) {
        Doctor doctor = doctorService.getDoctorByID(id);
        model.addAttribute(DOCTOR, doctor);
        return "doctor-details";
    }
    //пошук по фамилии
    @GetMapping("/doctors/searchBySurname")
public String searchDoctorsBySurname(Model model) {
    model.addAttribute(SEARCH_FORM, new DoctorSearchForm());
    return "doctor-search-surname";
}

    @PostMapping("/doctors/searchBySurname")
    public String processDoctorSearchBySurname(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model) {
        String surname = searchForm.getSurname();
        List<Doctor> doctors = doctorService.getAllDoctorsBySurname(surname);
        model.addAttribute(DOCTORS, doctors);
        return DOCTOR_SEARCH;
    }
    //пошук по имени
    @GetMapping("/doctors/searchByName")
    public String searchDoctorsByName(Model model) {
        model.addAttribute(SEARCH_FORM, new DoctorSearchForm());
        return "doctor-search-name";
    }

    @PostMapping("/doctors/searchByName")
    public String processDoctorSearchByName(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model) {
        String name = searchForm.getName();
        List<Doctor> doctors = doctorService.getAllDoctorsByName(name);
        model.addAttribute(DOCTORS, doctors);
        return DOCTOR_SEARCH;
    }
    // пошук по специализации
    @GetMapping("doctors/searchBySpecialization")
    public String searchDoctorsBySpecialization(Model model) {
        model.addAttribute(SEARCH_FORM, new DoctorSearchForm());
        return "doctor-search-specialization";
    }
    @PostMapping("/doctors/searchBySpecialization")
    public String processDoctorSearchBySpecialization(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model){
        Specialization specialization = searchForm.getSpecialization();
        List<Doctor> doctors = doctorService.getAllDoctorsBySpecialization(specialization);
        model.addAttribute(DOCTORS,doctors);
        return DOCTOR_SEARCH;
    }
    //форма создания и её обработка
    @GetMapping("/doctors/create")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute(DOCTOR, new Doctor());
        return "doctor-form";
    }
    @PostMapping("/doctors/create")
    public String processCreateDoctorForm(@ModelAttribute("doctor") Doctor doctor) {
        if(doctorService.checkDate(doctor.getBirthdayDate()))
            return "error-date-doctor";
        doctorService.create(doctor);
        return REDIRECT_DOCTOR;
    }
    //форма обновления и её оброботка
    @GetMapping("/doctors/update/{id}")
    public String showUpdateDoctorForm(@PathVariable Integer id, Model model) {
        Doctor doctor = doctorService.getDoctorByID(id);
        model.addAttribute(DOCTOR, doctor);
        return "doctor-update";
    }
    @PostMapping("/doctors/update/{id}")
    public String processUpdateDoctorForm(@PathVariable Integer id, @ModelAttribute("doctor") Doctor doctor) {
        if(doctorService.checkDate(doctor.getBirthdayDate()))
            return "error-date-doctor";
        doctorService.updateById(id, doctor);
        return REDIRECT_DOCTOR;
    }
    // вилучення доктора по id
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        doctorService.removeById(id);
        return REDIRECT_DOCTOR;
    }

}

