package com.med.medicalcentrecourse;
import com.med.medicalcentrecourse.model.Doctor;
import com.med.medicalcentrecourse.model.enums.Specialization;
import com.med.medicalcentrecourse.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testSaveDoctor() {
        // Создание объекта Doctor
        Doctor doctor = new Doctor();
        doctor.setName("John");
        doctor.setSurname("Doe");
        doctor.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor.setSpecialization(Specialization.Cardiologist);

        // Сохранение врача в репозитории
        doctorRepository.save(doctor);

        // Проверка сохраненного врача
        assertNotNull(doctor.getId());
        assertEquals(1, doctorRepository.count());
    }

    @Test
    public void testFindAllByName() {
        // Создание тестовых врачей
        Doctor doctor1 = new Doctor();
        doctor1.setName("John");
        doctor1.setSurname("Doe");
        doctor1.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor1.setSpecialization(Specialization.Cardiologist);
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Jane");
        doctor2.setSurname("Doe");
        doctor2.setBirthdayDate(LocalDate.of(1985, 5, 5));
        doctor2.setSpecialization(Specialization.Dermatologist);
        doctorRepository.save(doctor2);

        // Поиск врачей по имени
        List<Doctor> doctors = doctorRepository.findAllByName("John");

        // Проверка результата
        assertEquals(1, doctors.size());
        assertEquals("John", doctors.get(0).getName());
    }

    @Test
    public void testFindAllBySurname() {
        // Создание тестовых врачей
        Doctor doctor1 = new Doctor();
        doctor1.setName("John");
        doctor1.setSurname("Doe");
        doctor1.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor1.setSpecialization(Specialization.Cardiologist);
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Jane");
        doctor2.setSurname("Doe");
        doctor2.setBirthdayDate(LocalDate.of(1985, 5, 5));
        doctor2.setSpecialization(Specialization.EmergencyDoctor);
        doctorRepository.save(doctor2);

        // Поиск врачей по фамилии
        List<Doctor> doctors = doctorRepository.findAllBySurname("Doe");

        // Проверка результата
        assertEquals(2, doctors.size());
        assertEquals("Doe", doctors.get(0).getSurname());
        assertEquals("Doe", doctors.get(1).getSurname());
    }

    @Test
    public void testFindByNameIgnoreCaseAndSurnameIgnoreCaseAndSpecialization() {
        // Создание тестовых врачей
        Doctor doctor1 = new Doctor();
        doctor1.setName("John");
        doctor1.setSurname("Doe");
        doctor1.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor1.setSpecialization(Specialization.Cardiologist);
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Jane");
        doctor2.setSurname("Doe");
        doctor2.setBirthdayDate(LocalDate.of(1985, 5, 5));
        doctor2.setSpecialization(Specialization.Dermatologist);
        doctorRepository.save(doctor2);

        // Поиск врача по имени, фамилии и специализации (регистр не учитывается)
        Doctor foundDoctor = doctorRepository.findByNameIgnoreCaseAndSurnameIgnoreCaseAndSpecialization("john", "doe", Specialization.Cardiologist);

        // Проверка результата
        assertNotNull(foundDoctor);
        assertEquals("John", foundDoctor.getName());
        assertEquals("Doe", foundDoctor.getSurname());
        assertEquals(Specialization.Cardiologist, foundDoctor.getSpecialization());
    }

    @Test
    public void testFindAllBySpecialization() {
        // Создание тестовых врачей
        Doctor doctor1 = new Doctor();
        doctor1.setName("John");
        doctor1.setSurname("Doe");
        doctor1.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor1.setSpecialization(Specialization.Cardiologist);
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Jane");
        doctor2.setSurname("Doe");
        doctor2.setBirthdayDate(LocalDate.of(1985, 5, 5));
        doctor2.setSpecialization(Specialization.Dentist);
        doctorRepository.save(doctor2);

        // Поиск врачей по специализации
        List<Doctor> doctors = doctorRepository.findAllBySpecialization(Specialization.Cardiologist);

        // Проверка результата
        assertEquals(1, doctors.size());
        assertEquals(Specialization.Cardiologist, doctors.get(0).getSpecialization());
    }

    @Test
    public void testDeleteDoctor() {
        // Создание тестового врача
        Doctor doctor = new Doctor();
        doctor.setName("John");
        doctor.setSurname("Doe");
        doctor.setBirthdayDate(LocalDate.of(1980, 1, 1));
        doctor.setSpecialization(Specialization.Cardiologist);
        doctorRepository.save(doctor);

        // Удаление врача
        doctorRepository.delete(doctor);

        // Проверка удаления
        assertEquals(0, doctorRepository.count());
    }
}
