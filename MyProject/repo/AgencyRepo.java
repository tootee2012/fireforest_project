package com.example.MyProject.repo;

import com.example.MyProject.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepo extends JpaRepository<Agency,String> {
    Optional<Agency> findByAgencyEmailAndAgencyPassword(String email, String password);
}
