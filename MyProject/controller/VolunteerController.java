package com.example.MyProject.controller;


import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.User;
import com.example.MyProject.model.Volunteer;
import com.example.MyProject.repo.ApplyFormRepo;
import com.example.MyProject.repo.UserRepo;
import com.example.MyProject.repo.VolunteerRepo;
import com.example.MyProject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin
public class VolunteerController {

    @Autowired
    private VolunteerRepo repo;
    @Autowired
    private VolunteerService service;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApplyFormRepo applyFormRepo;


    //ทำยังไงก็ได้ให้มันดึง userEmail เข้ามา

    @PostMapping("/volunteer/{email}")
    private ResponseEntity<String> applyVolunteer(@PathVariable String email, @Validated @RequestBody Volunteer volunteer) {

        User user = userRepo.findById(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("ไม่พบผู้ใช้", HttpStatus.NOT_FOUND);
        }
        volunteer.setUser(user);

        if (volunteer.getApplicationDate() == null) {
            LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
            volunteer.setApplicationDate(dateTime.toString());
        }

        // เช็คเงื่อนไขโรคและอาหารที่แพ้
        boolean hasNoDiseasesAndAllergies =
                (volunteer.() == null || volunteer.getDiseases().trim().isEmpty()) &&
                        (volunteer.getAllergies() == null || volunteer.getAllergies().trim().isEmpty());

        if (hasNoDiseasesAndAllergies) {
            // ถ้าไม่มีโรคและอาหารที่แพ้ -> set วันที่ปัจจุบันและสถานะพร้อมทำงาน
            if (volunteer.getEntryDate() == null) {
                LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
                volunteer.setEntryDate(dateTime.toString());
            }
            volunteer.setVolunteerStatus("พร้อมทำงาน");
        } else {
            // ถ้ามีโรคหรืออาหารที่แพ้ -> สถานะรอการยืนยัน และไม่ set entry date
            volunteer.setVolunteerStatus("รอการยืนยัน");
            volunteer.setEntryDate(null); // ไม่กำหนดวันเริ่มงานก่อน
        }

        // ตรวจสอบค่าเริ่มต้นอื่นๆ
        if (volunteer.getVolunteerStatus() == null) {
            volunteer.setVolunteerStatus("รอการยืนยัน");
        }

        System.out.println("ได้รับข้อมูล: " + volunteer);
        String msg = service.registerVolunteer(volunteer);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
