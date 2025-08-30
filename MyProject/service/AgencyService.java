package com.example.MyProject.service;

import com.example.MyProject.model.Agency;
import com.example.MyProject.repo.AgencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepo repo;

    public String saveAgency(Agency agency){
        repo.save(agency);
        return "Agency registered successfully";
    }
}
