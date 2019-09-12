package com.javaadvanced2.hospital;

import com.javaadvanced2.hospital.model.Department;
import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.repository.DoctorRepository;
import com.javaadvanced2.hospital.service.DepartmentService;
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
    public CommandLineRunner demo(DoctorService doctorService, DepartmentService departmentService) {
        return (args) -> {
            Doctor doctor = new Doctor();
            doctor.setName("Ярослав");
            doctor.setMiddleName("Васильович");
            doctor.setSurname("Лупуляк");
            Department department = new Department();
            department.setName("Неврологія");
            department.setDescription("опис");
            departmentService.save(department);
            doctor.setDepartment(department);
            doctor.setQualificationLevel("Вища");
            doctor.setSpecialization("Нейрохірург");
            doctor.setPhotoName("kz1pqa.jpg");
            doctorService.save(doctor);

            doctor = new Doctor();
            doctor.setName("Ярослав");
            doctor.setMiddleName("Львович");
            doctor.setSurname("Криштафович");
            doctor.setDepartment(department);
            doctor.setQualificationLevel("Вища");
            doctor.setSpecialization("Невролог");
            doctor.setPhotoName("h56caz.jpg");
            doctorService.save(doctor);

            doctor = new Doctor();
            doctor.setName("Тарас");
            doctor.setMiddleName("Васильович");
            doctor.setSurname("Мельник");
            department = new Department();
            department.setName("Анестезіологія");
            department.setDescription("опис");
            departmentService.save(department);
            doctor.setDepartment(department);
            doctor.setQualificationLevel("Перша");
            doctor.setSpecialization("Анестезіолог");
            doctor.setPhotoName("ce1kz8.jpg");
            doctorService.save(doctor);

            doctor = new Doctor();
            doctor.setName("Тарас");
            doctor.setMiddleName("Михайлович");
            doctor.setSurname("Величко");
            department = new Department();
            department.setName("Педіатрія");
            department.setDescription("опис");
            departmentService.save(department);
            doctor.setDepartment(department);
            doctor.setQualificationLevel("Перша");
            doctor.setSpecialization("Педіатр");
            doctor.setPhotoName("c34tqa.jpg");
            doctorService.save(doctor);
        };
    }
}