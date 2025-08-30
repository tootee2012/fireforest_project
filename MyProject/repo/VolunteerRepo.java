package com.example.MyProject.repo;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepo extends JpaRepository<Volunteer,String> {
    List<Volunteer> findByUserUserEmail(String email);
}
