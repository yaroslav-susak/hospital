package com.itcluster.javaadvanced2.hospital;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.RoleRepository;
import com.itcluster.javaadvanced2.hospital.repository.UserRepository;
import com.itcluster.javaadvanced2.hospital.service.DepartmentService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DoctorService doctorService, DepartmentService departmentService, RoleRepository roleRepository, UserService userService) {
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

            Role admin = new Role();
            admin.setName("ADMIN");
            roleRepository.save(admin);
            Role user = new Role();
            user.setName("USER");
            roleRepository.save(user);
            Role author = new Role();
            author.setName("AUTHOR");
            roleRepository.save(author);

            User firstAdmin = new User();
            firstAdmin.setFirstName("Ярослав");
            firstAdmin.setLastName("Сусак");
            firstAdmin.setDateOfRegistration(new Date());
            firstAdmin.setEmail("susak.slava@gmail.com");
            Set<Role> adminAndAuthor = new HashSet<>();
            adminAndAuthor.add(admin);
            adminAndAuthor.add(author);
            firstAdmin.setRoles(adminAndAuthor);
            firstAdmin.setPassword("8!=40320");
            userService.createUpdate(firstAdmin);
        };
    }
}