package com.example.MyProject.service;

import com.example.MyProject.model.Experience;
import com.example.MyProject.repo.ExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepo repo;

    public String saveExperience(Experience experience){
        repo.save(experience);
        return "Experience saved successfully";
    }
}
