package com.example.MyProject.controller;

import com.example.MyProject.model.Agency;
import com.example.MyProject.model.FireForest;
import com.example.MyProject.repo.AgencyRepo;
import com.example.MyProject.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class AgencyController {
    @Autowired
    private AgencyRepo repo;
    @Autowired
    private AgencyService service;

    @PostMapping("/agency")
    private ResponseEntity<String> saveFireForest(@Validated @RequestBody Agency agency){
        System.out.println("ได้รับข้อมูล: " + agency);
        String msg = service.saveAgency(agency);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @PostMapping("/loginagency")
    public ResponseEntity<?> loginAgency(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        return repo.findByAgencyEmailAndAgencyPassword(email, password)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(Map.of("message", "Login success", "user", user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }
}
