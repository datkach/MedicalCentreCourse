package com.med.medicalcentrecourse;

import com.med.medicalcentrecourse.model.Patient;
import com.med.medicalcentrecourse.model.enums.Gender;
import com.med.medicalcentrecourse.repository.PatientsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PatientsRepositoryTest {

    @Autowired
    private PatientsRepository patientsRepository;

    @Test
    void testSavePatient() {
        // Создание объекта Patient
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAddress("123 Main St");
        patient.setPhoneNumber("123456789");
        patient.setGender(Gender.MALE);
        patient.setBloodGroup("A+");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));
        patient.setDoctorSurname("Smith");

        // Сохранение пациента в репозитории
        patientsRepository.save(patient);

        // Проверка сохраненного пациента
        assertNotNull(patient.getId());
        assertEquals(1, patientsRepository.count());
    }

    @Test
    void testFindAllByDoctorSurname() {
        // Создание тестовых пациентов
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAddress("123 Main St");
        patient1.setPhoneNumber("123456789");
        patient1.setGender(Gender.MALE);
        patient1.setBloodGroup("A+");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setDoctorSurname("Smith");
        patientsRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setAddress("456 Elm St");
        patient2.setPhoneNumber("987654321");
        patient2.setGender(Gender.FEMALE);
        patient2.setBloodGroup("B-");
        patient2.setBirthDate(LocalDate.of(1995, 5, 5));
        patient2.setDoctorSurname("Smith");
        patientsRepository.save(patient2);

        // Поиск пациентов по фамилии врача
        List<Patient> patients = patientsRepository.findAllByDoctorSurname("Smith");

        // Проверка результата
        assertEquals(2, patients.size());
        assertEquals("Smith", patients.get(0).getDoctorSurname());
        assertEquals("Smith", patients.get(1).getDoctorSurname());
    }

    // Тест для метода findAllByOrderByLastNameAsc
    @Test
    void testFindAllByOrderByLastNameAsc() {
        // Создание тестовых пациентов
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAddress("123 Main St");
        patient1.setPhoneNumber("123456789");
        patient1.setGender(Gender.MALE);
        patient1.setBloodGroup("A+");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setDoctorSurname("Smith");
        patientsRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Abd");
        patient2.setAddress("456 Elm St");
        patient2.setPhoneNumber("987654321");
        patient2.setGender(Gender.FEMALE);
        patient2.setBloodGroup("B-");
        patient2.setBirthDate(LocalDate.of(1995, 5, 5));
        patient2.setDoctorSurname("Smith");
        patientsRepository.save(patient2);

        // Поиск пациентов и сортировка по фамилии по возрастанию
        List<Patient> patients = patientsRepository.findAllByOrderByLastNameAsc();
        // Проверка результата
        assertEquals(2, patients.size());
        assertEquals("Abd", patients.get(0).getLastName());
        assertEquals("Doe", patients.get(1).getLastName());
    }

    // Тест для метода findAllByLastName
    @Test
    void testFindAllByLastName() {
        // Создание тестовых пациентов
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAddress("123 Main St");
        patient1.setPhoneNumber("123456789");
        patient1.setGender(Gender.MALE);
        patient1.setBloodGroup("A+");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setDoctorSurname("Smith");
        patientsRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Abd");
        patient2.setAddress("456 Elm St");
        patient2.setPhoneNumber("987654321");
        patient2.setGender(Gender.FEMALE);
        patient2.setBloodGroup("B-");
        patient2.setBirthDate(LocalDate.of(1995, 5, 5));
        patient2.setDoctorSurname("Smith");
        patientsRepository.save(patient2);

        // Поиск пациентов по фамилии
        List<Patient> patients = patientsRepository.findAllByLastName("Doe");

        // Проверка результата
        assertEquals(1, patients.size());
        assertEquals("Doe", patients.get(0).getLastName());
    }
    // Тест для метода findAllByFirstName
    @Test
    void testFindAllByFirstName() {
        // Создание тестовых пациентов
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAddress("123 Main St");
        patient1.setPhoneNumber("123456789");
        patient1.setGender(Gender.MALE);
        patient1.setBloodGroup("A+");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setDoctorSurname("Smith");
        patientsRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Abd");
        patient2.setAddress("456 Elm St");
        patient2.setPhoneNumber("987654321");
        patient2.setGender(Gender.FEMALE);
        patient2.setBloodGroup("B-");
        patient2.setBirthDate(LocalDate.of(1995, 5, 5));
        patient2.setDoctorSurname("Smith");
        patientsRepository.save(patient2);

        // Поиск пациентов по имени
        List<Patient> patients = patientsRepository.findAllByFirstName("John");

        // Проверка результата
        assertEquals(1, patients.size());
        assertEquals("John", patients.get(0).getFirstName());
    }

    // Тест для метода findAllByPhoneNumber
    @Test
    void testFindAllByPhoneNumber() {
        // Создание тестовых пациентов
        Patient patient1 = new Patient();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAddress("123 Main St");
        patient1.setPhoneNumber("123456789");
        patient1.setGender(Gender.MALE);
        patient1.setBloodGroup("A+");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setDoctorSurname("Smith");
        patientsRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Jane");
        patient2.setLastName("Abd");
        patient2.setAddress("456 Elm St");
        patient2.setPhoneNumber("987654321");
        patient2.setGender(Gender.FEMALE);
        patient2.setBloodGroup("B-");
        patient2.setBirthDate(LocalDate.of(1995, 5, 5));
        patient2.setDoctorSurname("Smith");
        patientsRepository.save(patient2);

        // Поиск пациентов по номеру телефона
        List<Patient> patients = patientsRepository.findAllByPhoneNumber("123456789");

        // Проверка результата
        assertEquals(1, patients.size());
        assertEquals("123456789", patients.get(0).getPhoneNumber());
    }

    // Аналогично напишите тесты для остальных методов: findAllByOrderByLastNameAsc,
    // findAllByLastName, findAllByFirstName, findAllByPhoneNumber.

    @Test
    void testDeletePatient() {
        // Создание тестового пациента
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAddress("123 Main St");
        patient.setPhoneNumber("123456789");
        patient.setGender(Gender.MALE);
        patient.setBloodGroup("A+");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));
        patient.setDoctorSurname("Smith");
        patientsRepository.save(patient);

        // Удаление пациента
        patientsRepository.delete(patient);

        // Проверка удаления
        assertEquals(0, patientsRepository.count());
    }
}



