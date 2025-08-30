package com.example.MyProject.repo;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.User;
import com.example.MyProject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FireForestRepo extends JpaRepository<FireForest,Integer> {
    List<FireForest> findByUserEmailUserEmailOrderByFireForestIdDesc(String email);

    List<FireForest> findByStatus(String status);



}
