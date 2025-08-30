package com.example.MyProject.repo;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Recruit_Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepo extends JpaRepository<Recruit_Form,Integer> {
}
