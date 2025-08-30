package com.example.MyProject.controller;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Fire_Picture;
import com.example.MyProject.repo.FirePictureRepo;
import com.example.MyProject.service.FirePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FirePictureController {
    @Autowired
    private FirePictureRepo repo;
    @Autowired
    private FirePictureService service;

    @PostMapping("/firepicture")
    private ResponseEntity<String> saveFireForest(@Validated @RequestBody Fire_Picture firePicture){
        System.out.println("ได้รับข้อมูล: " + firePicture);
        String msg = service.addFirePicture(firePicture);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/firepicture/{id}")
    private List<Fire_Picture> getFirePictureByFireForestId(@PathVariable int id){
        return  repo.findByFireForestFireForestId(id);
    }

}
