package com.example.MyProject.service;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Recruit_Form;
import com.example.MyProject.repo.RecruitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitRepo repo;

    public String saveRecruit(Recruit_Form recruitForm){
        repo.save(recruitForm);

        return "Recruit form registered successfully";
    }

    public List<Recruit_Form> getAllRecruit() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all recruit: " + e.getMessage());
        }
    }
}
