package com.example.MyProject.repo;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Fire_Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirePictureRepo extends JpaRepository<Fire_Picture,Integer> {
    List<Fire_Picture> findByFireForestFireForestId(int id);
}
