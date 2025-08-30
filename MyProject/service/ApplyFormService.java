package com.example.MyProject.service;

import com.example.MyProject.model.Apply_Form;
import com.example.MyProject.repo.ApplyFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyFormService {
    @Autowired
    private ApplyFormRepo repo;

    public String saveApplyForm(Apply_Form form){
        repo.save(form);
        return "ApplyForm saved successfully";
    }
}
