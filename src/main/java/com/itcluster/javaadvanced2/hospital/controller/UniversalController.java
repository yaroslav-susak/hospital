package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.CommentDTO;
import com.itcluster.javaadvanced2.hospital.dto.PasswordCheckingDTO;
import com.itcluster.javaadvanced2.hospital.dto.ReviewDTO;
import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.*;
import com.itcluster.javaadvanced2.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UniversalController {

    private static final String SUCCESSFULLY_CHANGED_DATA = "Дані успішно змінені";
    private static final String ERROR_CHANGED_DATA = "Помилка при зміні даних";
    private static final String ERROR_CHANGED_PASSWORD = "Помилка при зміні паролю";
    private static final String ERROR_CHANGED_PASSWORD2 = "Помилка! Паролі не однакові";

    @Autowired
    @Qualifier("basePath")
    private String basePath;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private QuestionService questionService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        if (authentication != null) {
            User user = userService.findUserByEmail(authentication.getName()).get();
            if (user.isBanned()) {
                throw new BannedUserException();
            } else {
                return user;
            }
        }
        return  null;
    }

    @GetMapping("/")
    public String homePage(Model model){
        doctorService.addSearchOptions(model);
        return "homepage";
    }

    @GetMapping("/search")
    public String findDoctorsByQualificationLevel(@RequestParam(name = "lvl", required = false) String qualification,
                                                  @RequestParam(name = "dep", required = false) String department,
                                                  @RequestParam(name = "sur", required = false) String surname,
                                                  Model model) {

        Set<Doctor> doctors = new HashSet<>(doctorService.findAll());

        if (qualification != null && !qualification.equals("")){
            doctors.retainAll(doctorService.findByQualificationLevel(qualification));
        }

        if(department!=null && !department.equals("")) {
            doctors.retainAll(doctorService.findByDepartment(departmentService.findByName(department)));
        }

        if(surname!=null && !surname.equals("")) {
            doctors.retainAll(doctorService.findBySurname(surname));
        }

        model.addAttribute("doctors",doctors);

        doctorService.addSearchOptions(model);
        return "staff";
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);

        doctorService.addSearchOptions(model);
        return "departments";
    }

    @GetMapping("/faq")
    public String getFaqList(Model model){
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions",questions);

        doctorService.addSearchOptions(model);
        return "faq";
    }

    @GetMapping("/diseases")
    public String getDiseases(Model model){
        Map<Department, Set<Disease>> diseasesByDepartment = new HashMap<>();

        List<Department> departments = departmentService.findAll();
        Set<Disease> tempDiseases;

        for (Department department : departments){
            tempDiseases = diseaseService.findByDepartment(department);
            diseasesByDepartment.put(department, tempDiseases);
            tempDiseases = null;
        }

        model.addAttribute("diseasesByDepartment", diseasesByDepartment);

        doctorService.addSearchOptions(model);
        return "diseases";
    }

    @GetMapping("/articles")
    public String getAllArticles(Model model){
        String type = "ARTICLE";
        List<News> allArticles = newsService.findByType(type);
        Collections.sort(allArticles);
        model.addAttribute("news", allArticles);
        model.addAttribute("type", type);
        doctorService.addSearchOptions(model);
        return "allnews";
    }

    @GetMapping("/news")
    public String getAllNews(Model model){
        String type = "NEWS";
        List<News> allNews = newsService.findByType(type);
        Collections.sort(allNews);
        model.addAttribute("news", allNews);
        model.addAttribute("type", type);
        doctorService.addSearchOptions(model);
        return "allnews";
    }

    @GetMapping("/doctor-info/{id}")
    public String doctorById(@PathVariable Long id, Model model, @ModelAttribute User user){
        Doctor doctor = doctorService.findById(id);
        List<Review> reviews = reviewService.findByDoctor(doctor);
        Collections.sort(reviews);

        boolean userAlreadyLeftReview = false;

        if(user != null) {
            reviewService.userReviewFirst(reviews, user);
            userAlreadyLeftReview = reviewService.userAlreadyLeftReview(reviews, user);
        }

        model.addAttribute("doctor", doctor);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewDTO", new ReviewDTO());
        model.addAttribute("userAlreadyLeftReview", userAlreadyLeftReview);
        model.addAttribute("admin", roleService.getByName("ADMIN"));
        doctorService.addSearchOptions(model);
        return "doctor";
    }

    @GetMapping("/doctor-info/{id}/timetable")
    public String giveTimetable(@PathVariable Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        List<Schedule> schedules = scheduleService.findActiveByDoctor(doctor);
        ScheduleGenerateDTO dto = new ScheduleGenerateDTO();

        model.addAttribute("doctor", doctor);
        model.addAttribute("schedules", schedules);
        model.addAttribute("scheduleDTO", dto);
        model.addAttribute("hoursToStart", scheduleService.getHours(23));
        model.addAttribute("durationsHours", scheduleService.getHours(8));

        doctorService.addSearchOptions(model);
        return "timetable";
    }

    @GetMapping("/news/{id}")
    public String getNewsById(@PathVariable Long id,
                              @ModelAttribute User user,
                              Model model){
        newsService.formNewsOrArticle(id, user, model);
        model.addAttribute("admin", roleService.getByName("ADMIN"));
        doctorService.addSearchOptions(model);
        return "news";
    }

    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable Long id,
                              @ModelAttribute User user,
                              Model model){
        newsService.formNewsOrArticle(id, user, model);
        model.addAttribute("admin", roleService.getByName("ADMIN"));
        doctorService.addSearchOptions(model);
        return "news";
    }

    @GetMapping("/doctor-info/{id}/free-schedule")
    public String deleteUserFromSchedule(@RequestParam(name="scheduleId") Long scheduleId,
                                         @RequestParam(name="patientId") Long patientId,
                                         @PathVariable Long id,
                                         Model model){
        scheduleService.deletePatientFromSchedule(scheduleId, patientId);
        return "redirect:/doctor-info/{id}/timetable";
    }

    @GetMapping("/doctor-info/{id}/reserve-time")
    public String setUserForSchedule(@RequestParam(name="scheduleId") Long scheduleId,
                                     @RequestParam(name="patientId") Long patientId,
                                     @PathVariable Long id,
                                     Model model){
        scheduleService.setPatientForSchedule(scheduleId, patientId);
        return "redirect:/doctor-info/{id}/timetable";
    }

    @GetMapping("/cabinet")
    public String userCabinet(@ModelAttribute("user") User user, Model model) {
        List<Schedule> schedules = null;

        if (user.getRoles().contains(roleService.getByName("PATIENT"))){
            schedules = scheduleService.findActiveByUser(user);
        }

        if(user.getRoles().contains(roleService.getByName("AUTHOR"))){
            model.addAttribute("news", newsService.findNewsByAuthor(user));
            model.addAttribute("articles", newsService.findArticlesByAuthor(user));
        }

        if (user.getRoles().contains(roleService.getByName("DOCTOR"))){
            Doctor doctor = null;

            for(Doctor doc : doctorService.findAll()){
                if (doc.getUser().equals(user)){
                    doctor = doc;
                }
            }
            model.addAttribute("doctor", doctor);
            model.addAttribute("scheduleDTO", new ScheduleGenerateDTO());
            model.addAttribute("hoursToStart", scheduleService.getHours(23));
            model.addAttribute("durationsHours", scheduleService.getHours(8));

            schedules = scheduleService.findActiveByDoctor(doctor);
        }

        model.addAttribute("schedules", schedules);

        doctorService.addSearchOptions(model);
        return "cabinet";
    }

    @PostMapping("/leave-review")
    public String leaveReview(@ModelAttribute ReviewDTO reviewDTO, @ModelAttribute User user){
        Doctor doctor = doctorService.findById(reviewDTO.getDoctorId());

        Review review = new Review();
        review.setDoctor(doctor);
        review.setPatient(user);
        review.setDate(new Date());
        review.setText(reviewDTO.getText());
        reviewService.save(review);

        return "redirect:/doctor-info/" + doctor.getId();
    }

    @DeleteMapping("/delete-review")
    public String deleteReview(@ModelAttribute ReviewDTO reviewDTO,
                               @ModelAttribute User user,
                               @RequestParam(name="reviewId") Long id){
        Review review = reviewService.findById(id);
        if (review.getPatient().equals(user) ||
                user.getRoles().contains(roleService.getByName("ADMIN"))){
            reviewService.delete(review);
        }
        return "redirect:/doctor-info/" + reviewDTO.getDoctorId();
    }

    @PostMapping("/edit-review")
    public String editReview(@ModelAttribute ReviewDTO reviewDTO,
                             @ModelAttribute User user,
                             @RequestParam(name="reviewId") Long id){
        Review review = reviewService.findById(id);
        if (review.getPatient().equals(user)){
            review.setText(reviewDTO.getText());
            review.setDate(new Date());
            reviewService.save(review);
        }
        return "redirect:/doctor-info/" + reviewDTO.getDoctorId();
    }

    @PostMapping("/leave-comment")
    public String leaveComment(@ModelAttribute CommentDTO commentDTO,
                               @ModelAttribute User user){
        Comment comment = new Comment();
        News news = newsService.findById(commentDTO.getNewsId());

        comment.setNews(news);
        comment.setText(commentDTO.getText());
        comment.setUser(user);
        comment.setDate(new Date());

        commentService.save(comment);
        return "redirect:/news/" + news.getId();
    }

    @DeleteMapping("/delete-comment")
    public String deleteReview(@ModelAttribute CommentDTO commentDTO,
                               @ModelAttribute User user,
                               @RequestParam(name="commentId") Long id){
        Comment comment = commentService.findById(id);

        if (comment.getUser().equals(user) ||
                user.getRoles().contains(roleService.getByName("ADMIN"))){
            commentService.delete(comment);
        }

        return "redirect:/news/" + comment.getNews().getId();
    }

    @PostMapping("/edit-comment")
    public String editReview(@ModelAttribute CommentDTO commentDTO,
                             @ModelAttribute User user,
                             @RequestParam(name="commentId") Long id){
        Comment comment = commentService.findById(id);

        if (comment.getUser().equals(user)){
            comment.setText(commentDTO.getText());
            comment.setDate(new Date());
            commentService.save(comment);
        }

        return "redirect:/news/" + comment.getNews().getId();
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
            return "redirect:/cabinet";
        }
        if(!passwordCheckingDTO.getPasswordRawFir().equals(passwordCheckingDTO.getPasswordRawSec())){
            bindingResult.rejectValue("passwordRawSec",ERROR_CHANGED_PASSWORD2);
            model.addAttribute("dtoPass", passwordCheckingDTO);
            return "redirect:/cabinet";
        }else{
            user.setPassword(coder.encode(passwordCheckingDTO.getPasswordRawFir()));
            userService.createUpdate(user);
        }
        return "redirect:/cabinet";
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

        return "redirect:/cabinet";
    }

    @PostMapping("/save")
    public String saveUser(User user)
    {
        userService.createUpdate(user);
        return "redirect:/logout";
    }
}
