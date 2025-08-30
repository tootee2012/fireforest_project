package com.example.MyProject.controller;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Recruit_Form;
import com.example.MyProject.model.User;
import com.example.MyProject.repo.RecruitRepo;
import com.example.MyProject.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RecruitController {

    @Autowired
    private RecruitRepo repo;
    @Autowired
    private RecruitService service;

    @PostMapping("/recruit")
    private ResponseEntity<String> addRecruitForm(@RequestBody Recruit_Form form){
        //save the user
        System.out.println("ได้รับข้อมูล: " + form);
        String msg = service.saveRecruit(form);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/recruit")
    public ResponseEntity<?> getAllRecruit(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Recruit_Form> recruits = service.getAllRecruit();

            return ResponseEntity.ok(Map.of(
                    "data", recruits,
                    "count", recruits.size()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch fire forest reports"));
        }
    }
}
