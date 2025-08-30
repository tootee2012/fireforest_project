package com.example.MyProject.repo;

import com.example.MyProject.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

// use in volunteer controller
public interface ExperienceRepo extends JpaRepository<Experience,Integer> {
}
