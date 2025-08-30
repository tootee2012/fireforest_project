package com.example.MyProject.service;

import com.example.MyProject.model.Fire_Forest_Detail;
import com.example.MyProject.repo.FireForestDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireForestDetailService {
    @Autowired
    private FireForestDetailRepo repo;

    public String saveFireForestDetail(Fire_Forest_Detail fireForestDetail){
        repo.save(fireForestDetail);

        return "FireForestDetail saved successfully";
    }
}
