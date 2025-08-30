package com.example.MyProject.service;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Volunteer;
import com.example.MyProject.repo.UserRepo;
import com.example.MyProject.repo.VolunteerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



// check userEmail ด้วยว่ามีในระบบไหม

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepo repo;
    @Autowired
    private UserRepo userRepo;

    public String registerVolunteer(Volunteer volunteer){
        repo.save(volunteer);
        return "Volunteer registered successfully";
    }
}
