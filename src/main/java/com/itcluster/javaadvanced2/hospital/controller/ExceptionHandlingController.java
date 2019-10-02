package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
@Slf4j
public class ExceptionHandlingController {

    @Autowired
    private DoctorService doctorService;

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex, Model model) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);

        model.addAttribute("message", ex.getMessage());

        doctorService.addSearchOptions(model);
        return "error";
    }
}
