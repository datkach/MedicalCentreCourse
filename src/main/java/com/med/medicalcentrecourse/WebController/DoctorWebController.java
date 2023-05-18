package com.med.medicalcentrecourse.WebController;

import ch.qos.logback.core.pattern.SpacePadder;
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
// получаем всех докторов
    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getALlDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/doctors";
    }
// получаем доктора по id
    @GetMapping("/doctors/{id}")
    public String getDoctorById(@PathVariable Integer id, Model model) {
        Doctor doctor = doctorService.getDoctorByID(id);
        model.addAttribute("doctor", doctor);
        return "doctor/doctor-details";
    }
    //пошук по фамилии
    @GetMapping("/doctors/searchBySurname")
public String searchDoctorsBySurname(Model model) {
    model.addAttribute("searchForm", new DoctorSearchForm());
    return "doctor/doctor-search-surname";
}

    @PostMapping("/doctors/searchBySurname")
    public String processDoctorSearchBySurname(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model) {
        String surname = searchForm.getSurname();
        List<Doctor> doctors = doctorService.getAllDoctorsBySurname(surname);
        model.addAttribute("doctors", doctors);
        return "doctor/doctor-search-results";
    }
    //пошук по имени
    @GetMapping("/doctors/searchByName")
    public String searchDoctorsByName(Model model) {
        model.addAttribute("searchForm", new DoctorSearchForm());
        return "doctor/doctor-search-name";
    }

    @PostMapping("/doctors/searchByName")
    public String processDoctorSearchByName(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model) {
        String name = searchForm.getName();
        List<Doctor> doctors = doctorService.getAllDoctorsByName(name);
        model.addAttribute("doctors", doctors);
        return "doctor/doctor-search-results";
    }
    // пошук по специализации
    @GetMapping("doctors/searchBySpecialization")
    public String searchDoctorsBySpecialization(Model model) {
        model.addAttribute("searchForm" , new DoctorSearchForm());
        return "doctor/doctor-search-specialization";
    }
    @PostMapping("/doctors/searchBySpecialization")
    public String processDoctorSearchBySpecialization(@ModelAttribute("searchForm") DoctorSearchForm searchForm, Model model){
        Specialization specialization = searchForm.getSpecialization();
        List<Doctor> doctors = doctorService.getAllDoctorsBySpecialization(specialization);
        model.addAttribute("doctors",doctors);
        return "doctor/doctor-search-results";
    }
    //форма создания и её обработка
    @GetMapping("/doctors/create")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/doctor-form";
    }
    @PostMapping("/doctors/create")
    public String processCreateDoctorForm(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.create(doctor);
        return "redirect:/doctors";
    }
    //форма обновления и её оброботка
    @GetMapping("/doctors/update/{id}")
    public String showUpdateDoctorForm(@PathVariable Integer id, Model model) {
        Doctor doctor = doctorService.getDoctorByID(id);
        model.addAttribute("doctor", doctor);
        return "doctor/doctor-update";
    }
    @PostMapping("/doctors/update/{id}")
    public String processUpdateDoctorForm(@PathVariable Integer id, @ModelAttribute("doctor") Doctor doctor) {
        doctorService.updateById(id, doctor);
        return "redirect:/doctors";
    }
    // вилучення доктора по id
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        doctorService.removeById(id);
        return "redirect:/doctors";
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public String handleResourceNotFoundException() {
//        return "error";
//    }
}

