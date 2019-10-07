package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.PasswordCheckingDTO;
import com.itcluster.javaadvanced2.hospital.dto.ReviewDTO;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Review;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.ReviewService;
import com.itcluster.javaadvanced2.hospital.service.ScheduleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private static final String SUCCESSFULLY_CHANGED_DATA = "Дані успішно змінені";
    private static final String ERROR_CHANGED_DATA = "Помилка при зміні даних";
    private static final String ERROR_CHANGED_PASSWORD = "Помилка при зміні паролю";
    private static final String ERROR_CHANGED_PASSWORD2 = "Помилка! Паролі не однакові";

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    @Qualifier("basePath")
    private String basePath;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping("/cabinet")
    public String userCabinet(@ModelAttribute("user") User user,Model model) {
        List<Schedule> schedules = scheduleService.findActiveByUser(user);
        model.addAttribute("schedules",schedules);

        doctorService.addSearchOptions(model);
        return "cabinet";
    }

    @PostMapping("/save")
    public String saveUser(User user)
    {
        userService.createUpdate(user);
        return "cabinet";
    }

    @GetMapping("/passwordchange")
    public String changePassword(Model model)
    {
        model.addAttribute("dtoPass", new PasswordCheckingDTO());
        doctorService.addSearchOptions(model);
        return "passwordchange";
    }

    @PostMapping("/savePassword")
    public String savePassword(@ModelAttribute("user") User user, PasswordCheckingDTO passwordCheckingDTO,
                               BindingResult bindingResult,
                               Model model)
    {
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder();

        if(!coder.matches(passwordCheckingDTO.getPasswordOld(),user.getPassword()))
        {
            bindingResult.rejectValue("passwordOld",ERROR_CHANGED_PASSWORD);
            model.addAttribute("dtoPass", passwordCheckingDTO);
            return "passwordchange";
        }
        if(!passwordCheckingDTO.getPasswordRawFir().equals(passwordCheckingDTO.getPasswordRawSec())){
            bindingResult.rejectValue("passwordRawSec",ERROR_CHANGED_PASSWORD2);
            model.addAttribute("dtoPass", passwordCheckingDTO);
            return "passwordchange";
        }else{
            User user1 = user;
            user1.setPassword(coder.encode(passwordCheckingDTO.getPasswordRawFir()));
            userService.createUpdate(user1);
        }
        return "cabinet";
    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("file") MultipartFile file,
                              @ModelAttribute("user") User user
    ) {
        String uploadName = "userPhoto"+user.getId()+".jpg";

        try {
            File transferFile = new File(basePath +"/" + uploadName);
            file.transferTo(transferFile);
            user.setPhoto(uploadName);
            userService.createUpdate(user);
        } catch (IOException e) {
            System.out.println("Error saving file");
        }

        return "redirect:/user/cabinet";
    }

    @PostMapping("/leave-review")
    public String leaveReview(@ModelAttribute ReviewDTO reviewDTO, @ModelAttribute User user){
        Doctor doctor = doctorService.findById(reviewDTO.getDoctorId());
        User patient = userService.findById(reviewDTO.getPatientId());

        Review review = new Review();
        review.setDoctor(doctor);
        review.setPatient(patient);
        review.setDate(new Date());
        review.setText(reviewDTO.getText());
        reviewService.save(review);

        return "redirect:/doctor-info/" + doctor.getId();
    }
}
