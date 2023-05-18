package com.med.medicalcentrecourse.restControllers;

import com.med.medicalcentrecourse.dto.DoctorRequestDto;
import com.med.medicalcentrecourse.dto.DoctorResponseDto;
import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.service.DoctorService;
import com.med.medicalcentrecourse.util.mapper.DoctorMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponseDto saveEmployee(@RequestBody @Valid DoctorRequestDto doctor) {
        return DoctorMapper.INSTANCE.toDto(doctorService
                .create(DoctorMapper.INSTANCE.fromDto(doctor)));
    }
    @PutMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor refreshDoctor(@PathVariable("id") Integer id, @RequestBody Doctor doctor){
        return doctorService.updateById(id,doctor);
    }
    //Получение списка юзеров
    @GetMapping("/doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getAllUsers() {
        return doctorService.getALlDoctors();
    }
    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getDoctorById(@PathVariable("id") Integer id){
        return doctorService.getDoctorByID(id);
    }
    @GetMapping("/doctor/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getDoctorByName(@RequestParam String name){
        return doctorService.getAllDoctorsByName(name);
    }
    @GetMapping("/doctor/surname")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getDoctorBySurname(@RequestParam String surname){
        return doctorService.getAllDoctorsBySurname(surname);
    }
    @GetMapping("/doctor/specialization")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getDoctorBySpecialization(@RequestParam Specialization specialization){
        return doctorService.getAllDoctorsBySpecialization(specialization);
    }
    //Удаление по id
    @PatchMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        doctorService.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/doctor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        doctorService.removeAll();
    }
}
