package com.example.MyProject.service;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.Fire_Picture;
import com.example.MyProject.model.User;
import com.example.MyProject.repo.FireForestRepo;
import com.example.MyProject.repo.FirePictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireForestService {

    @Autowired
    private FireForestRepo repo;

    @Autowired
    private FirePictureRepo pictureRepo;

    public String saveFireForest(FireForest fireForest) {
        try {
            repo.save(fireForest);
            return "Fire forest report saved successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to save fire forest report: " + e.getMessage());
        }
    }

    public String saveFireForestAndPicture(FireForest fireForest , List<String> pictureUrls) {
        try {
            FireForest savedFireForest = repo.save(fireForest);
            for(String url : pictureUrls){
                Fire_Picture picture = new Fire_Picture();
                picture.setPictureURL(url);
                picture.setFireForest(savedFireForest);
                pictureRepo.save(picture);
            }
            return "Fire forest report and picture saved successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to save fire forest report: " + e.getMessage());
        }
    }

    public List<FireForest> getFireForestByUserEmail(String email) {
        try {
            return repo.findByUserEmailUserEmailOrderByFireForestIdDesc(email); // สมมติว่ามี method นี้ใน repo
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch fire forest reports: " + e.getMessage());
        }
    }

    public List<FireForest> getAllFireForest() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all fire forest reports: " + e.getMessage());
        }
    }

    public String updateFireForestStatus(int id, String status) {
        try {
            FireForest fireForest = repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Fire forest report not found"));

            fireForest.setStatus(status);
            repo.save(fireForest);

            return "Status updated successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update status: " + e.getMessage());
        }
    }

    public FireForest getFireForestById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fire forest report not found"));
    }

    public List<FireForest> getFireForestByStatus(String status) {
        return repo.findByStatus(status); // ต้องเพิ่มใน repo
    }
}
