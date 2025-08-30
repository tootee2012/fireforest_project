package com.example.MyProject.controller;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.service.FireForestService;
import com.example.MyProject.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://10.0.2.2:8080")
public class FireForestController {

    @Autowired
    private FireForestService service;

    @Autowired
    private GeoService geoService; // เพิ่ม GeoService

    @PostMapping("/fireforest")
    public ResponseEntity<?> saveFireForest(@Validated @RequestBody FireForest fireForest) {
        try {
            System.out.println("ได้รับข้อมูล: " + fireForest);

            // Validate required fields
            if (fireForest.getFireForestLocation() == null ||
                    fireForest.getFireForestLocation().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Fire location is required"));
            }

            // Set timestamp if not provided
            if (fireForest.getFireForestTime() == null) {
                LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
                fireForest.setFireForestTime(dateTime.toString());
            }

            // Get coordinates from address
            double[] latLng = geoService.getLatLngFromAddress(fireForest.getFireForestLocation());
            if (latLng != null) {
                fireForest.setFireForestLat(String.valueOf(latLng[0]));
                fireForest.setFireForestLong(String.valueOf(latLng[1]));
            } else {
                System.out.println("Warning: Could not geocode address: " + fireForest.getFireForestLocation());
            }

            // Set default status if not provided
            if (fireForest.getStatus() == null || fireForest.getStatus().trim().isEmpty()) {
                fireForest.setStatus("PENDING");
            }

            String msg = service.saveFireForest(fireForest);


            return ResponseEntity.ok(Map.of(
                    "message", msg,
                    "fireForestId", fireForest.getFireForestId(),
                    "coordinates", latLng != null ?
                            Map.of("lat", latLng[0], "lng", latLng[1]) : "Not geocoded",
                    "status", fireForest.getStatus()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save fire forest report: " + e.getMessage()));
        }
    }

    @PostMapping("/fireforestandpicture")
    public ResponseEntity<?> saveFireForestAndPicture(@Validated @RequestBody FireForest fireForest , List<String> url) {
        try {
            System.out.println("ได้รับข้อมูล: " + fireForest);

            // Validate required fields
            if (fireForest.getFireForestLocation() == null ||
                    fireForest.getFireForestLocation().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Fire location is required"));
            }

            // Set timestamp if not provided
            if (fireForest.getFireForestTime() == null) {
                LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
                fireForest.setFireForestTime(dateTime.toString());
            }
            // Get coordinates from address
            double[] latLng = geoService.getLatLngFromAddress(fireForest.getFireForestLocation());
            if (latLng != null) {
                fireForest.setFireForestLat(String.valueOf(latLng[0]));
                fireForest.setFireForestLong(String.valueOf(latLng[1]));
            } else {
                System.out.println("Warning: Could not geocode address: " + fireForest.getFireForestLocation());
            }

            // Set default status if not provided
            if (fireForest.getStatus() == null || fireForest.getStatus().trim().isEmpty()) {
                fireForest.setStatus("PENDING");
            }

            String msg = service.saveFireForest(fireForest);


            return ResponseEntity.ok(Map.of(
                    "message", msg,
                    "fireForestId", fireForest.getFireForestId(),
                    "coordinates", latLng != null ?
                            Map.of("lat", latLng[0], "lng", latLng[1]) : "Not geocoded",
                    "status", fireForest.getStatus()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save fire forest report: " + e.getMessage()));
        }
    }
    @GetMapping("/fireforest/{email}")
    public ResponseEntity<?> getFireForestByUserId(@PathVariable String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email is required"));
            }

            List<FireForest> fireForests = service.getFireForestByUserEmail(email);

            return ResponseEntity.ok(Map.of(
                    "data", fireForests,
                    "count", fireForests.size(),
                    "email", email
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch fire forest reports: " + e.getMessage()));
        }
    }


    @GetMapping("/fireforest")
    public ResponseEntity<?> getAllFireForest(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<FireForest> fireForests = service.getAllFireForest();

            return ResponseEntity.ok(Map.of(
                    "data", fireForests,
                    "count", fireForests.size()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch fire forest reports"));
        }
    }

    // เพิ่ม endpoint สำหรับ update status
    @PutMapping("/fireforest/{id}/status")
    public ResponseEntity<?> updateFireForestStatus(
            @PathVariable int id,
            @RequestBody Map<String, String> statusUpdate) {
        try {
            String newStatus = statusUpdate.get("status");
            if (newStatus == null || newStatus.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Status is required"));
            }

            String msg = service.updateFireForestStatus(id, newStatus);

            return ResponseEntity.ok(Map.of(
                    "message", msg,
                    "fireForestId", id,
                    "newStatus", newStatus
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update status: " + e.getMessage()));
        }
    }
}