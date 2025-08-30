package com.example.MyProject.controller;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Fire_Forest_Detail;
import com.example.MyProject.model.User;
import com.example.MyProject.model.Volunteer;
import com.example.MyProject.repo.FireForestDetailRepo;
import com.example.MyProject.repo.FireForestRepo;
import com.example.MyProject.service.FireForestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FireForestDetailController {
    @Autowired
    private FireForestDetailRepo repo;
    @Autowired
    private FireForestDetailService service;
    @Autowired
    private FireForestRepo fireForestRepo;

    //อย่าลืมทำ put เพื่อใส่ข้อมูลรายงานด้วย

    @PostMapping("/fireforestdetail/{id}")
    private ResponseEntity<String> applyVolunteer(@PathVariable Integer id, @Validated @RequestBody Fire_Forest_Detail fireForestDetail) {

        FireForest fireForest = fireForestRepo.findById(id).orElse(null);
        if (fireForest == null) {
            return new ResponseEntity<>("ไม่พบรหัสไฟป่า", HttpStatus.NOT_FOUND);
        }
        fireForestDetail.setFireForest(fireForest);

        System.out.println("ได้รับข้อมูล: " + fireForestDetail);
        String msg = service.saveFireForestDetail(fireForestDetail);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
