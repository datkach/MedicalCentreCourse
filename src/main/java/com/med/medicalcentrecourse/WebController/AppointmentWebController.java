package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Appointment;
import com.med.medicalcentrecourse.model.Diagnosis;
import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.service.AppointmentService;
import com.med.medicalcentrecourse.service.DoctorService;
import com.med.medicalcentrecourse.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AppointmentWebController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorServiceBean;
    private final PatientsService patientsServiceBean;

    @PostMapping("/appointment")
    public String createAppointment(@ModelAttribute("appointment") Appointment appointment) {
        validateAppointment(appointment);
        appointmentService.create(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointment/{id}")
    public String getAppointmentById(@PathVariable("id") Integer id, Model model) {
        Appointment appointment = appointmentService.getAppointmentByID(id);
        model.addAttribute("appointment", appointment);
        return "appointment/appointment-details";
    }

    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getALlAppointment();
        model.addAttribute("appointments", appointments);
        return "appointment/appointments";
    }

    @GetMapping("/appointments/{id}")
    public String getAppointmentByPersonId(@PathVariable("id") Integer id, Model model) {
        List<Appointment> appointments = appointmentService.getAppointmentByPersonID(id);
        model.addAttribute("appointments", appointments);
        return "appointment/appointments-by-person";
    }

    @GetMapping("/diagnosis/{id}")
    public String getAllDiagnosis(@PathVariable("id") Integer id, Model model) {
        Set<String> diagnoses = appointmentService.getAllDiagnoseByPatientID(id);
        model.addAttribute("diagnoses", diagnoses);
        return "appointment/diagnoses";
    }
    @GetMapping("/appointment/create")
    public String showCreateAppointmentForm(Model model) {
        // Получить список всех пациентов
        List<Patient> patients = patientsServiceBean.getAllPatients();

        // Получить список всех врачей
        List<Doctor> doctors = doctorServiceBean.getALlDoctors();

        // Получить список всех диагнозов
        List<Diagnosis> allDiagnoses = appointmentService.getAllDiagnosis();

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        model.addAttribute("allDiagnoses", allDiagnoses);

        return "appointment/appointment-form";
    }
    @GetMapping("/appointment/update/{id}")
    public String showUpdateAppointmentForm(@PathVariable Integer id, Model model) {
        Appointment appointment = appointmentService.getAppointmentByID(id);
        model.addAttribute("appointment", appointment);
        return "appointment/appointment-form";
    }

    @PostMapping("/appointment/update/{id}")
    public String processUpdateAppointmentForm(@PathVariable Integer id, @ModelAttribute("appointment") Appointment appointment) {
        appointmentService.updateById(id, appointment);
        return "redirect:/appointments";
    }

    @DeleteMapping("/appointment/{id}")
    public String removeAppointmentById(@PathVariable Integer id) {
        appointmentService.removeById(id);
        return "redirect:/appointments";
    }

    @DeleteMapping("/appointment")
    public String removeAllAppointments() {
        appointmentService.removeAll();
        return "redirect:/appointments";
    }

    // Служебный метод
    private void validateAppointment(Appointment appointment) {
        if (checkDate(appointment.getActionTime())) {
            throw new IllegalArgumentException("Invalid action time");
        }
    }

    private boolean checkDate(LocalDateTime date) {
        return date.getYear() < LocalDateTime.now().minusYears(100).getYear()
                || date.getYear() > LocalDateTime.now().plusYears(30).getYear();
    }
}