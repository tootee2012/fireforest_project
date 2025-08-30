package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fireforest")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FireForest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fire_forest_id")
    private int fireForestId;

    @Column(name = "fireForestTime")
    private String fireForestTime;

    @Column(name = "fireForestLocation", nullable = false)
    private String fireForestLocation;

    @Column(name = "fireForestDetail")
    private String fireForestDetail;

    @Column(length = 50)
    private String status;

    @Column(length = 50)
    private String field;

    @Column(name = "fireForestLat")
    private String fireForestLat;

    @Column(name = "fireForestLong")
    private String fireForestLong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "user_email")
    @JsonBackReference(value = "user-fireforests")
    @ToString.Exclude
    private User userEmail;

    @OneToOne(mappedBy = "fireForest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "ff-detail")
    @ToString.Exclude
    private Fire_Forest_Detail reportFireForest;

    @OneToMany(mappedBy = "fireForest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "ff-pictures")
    @ToString.Exclude
    private List<Fire_Picture> firePicture;

    public Double getLatitudeAsDouble() {
        try {
            return fireForestLat != null ? Double.parseDouble(fireForestLat) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Double getLongitudeAsDouble() {
        try {
            return fireForestLong != null ? Double.parseDouble(fireForestLong) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void setCoordinates(double lat, double lng) {
        this.fireForestLat = String.valueOf(lat);
        this.fireForestLong = String.valueOf(lng);
    }
}