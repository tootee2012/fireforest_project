package com.example.MyProject.controller;

import com.example.MyProject.model.Agency;
import com.example.MyProject.model.Apply_Form;
import com.example.MyProject.repo.ApplyFormRepo;
import com.example.MyProject.service.ApplyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin
public class ApplyFormController {
    @Autowired
    private ApplyFormRepo repo;
    @Autowired
    private ApplyFormService service;



    @PostMapping("/applyform")
    private ResponseEntity<String> saveApplyForm(@Validated @RequestBody Apply_Form form){
        if (form.getFormDate() == null) {
            LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
            form.setFormDate(dateTime.toString());
        }
        System.out.println("ได้รับข้อมูล: " + form);
        String msg = service.saveApplyForm(form);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
