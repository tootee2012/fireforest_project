package com.example.MyProject.repo;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Fire_Forest_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireForestDetailRepo extends JpaRepository<Fire_Forest_Detail,Integer> {
}
