package com.javaadvanced2.hospital;

import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.repository.DoctorRepository;
import com.javaadvanced2.hospital.service.DoctorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DoctorService doctorService) {
        return (args) -> {
            Doctor doctor = new Doctor();
            doctor.setName("Ярослав");
            doctor.setMiddleName("Васильович");
            doctor.setSurname("Лупуляк");
            doctor.setDepartment("Хірургія");
            doctor.setQualificationLevel("Вища");
            doctor.setSpecialization("Нейрохірург");
            doctorService.save(doctor);

            doctor = new Doctor();
            doctor.setName("Тарас");
            doctor.setMiddleName("Васильович");
            doctor.setSurname("Мельник");
            doctor.setDepartment("Анестезіологія");
            doctor.setQualificationLevel("Перша");
            doctor.setSpecialization("Анестезіолог");
            doctorService.save(doctor);
        };
    }
}