package com.example.MyProject.service;

import com.example.MyProject.model.Fire_Picture;
import com.example.MyProject.repo.FirePictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirePictureService {
    @Autowired
    private FirePictureRepo repo;

    public String addFirePicture(Fire_Picture firePicture){
        repo.save(firePicture);
        return "FirePicture add successfully";
    }
}
