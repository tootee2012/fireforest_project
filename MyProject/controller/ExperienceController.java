package com.example.MyProject.controller;

import com.example.MyProject.model.Agency;
import com.example.MyProject.model.Experience;
import com.example.MyProject.repo.ExperienceRepo;
import com.example.MyProject.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ExperienceController {
    @Autowired
    private ExperienceRepo repo;
    @Autowired
    private ExperienceService service;

    @PostMapping("/experience")
    private ResponseEntity<String> saveFireForest(@Validated @RequestBody Experience experience){
        System.out.println("ได้รับข้อมูล: " + experience);
        String msg = service.saveExperience(experience);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
