package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "picture_id")
@Entity
@Table(name = "fire_picture")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Fire_Picture {
    @Id
    @GeneratedValue
    private int picture_id;
    private String pictureURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fire_forest_id")
    @JsonBackReference(value = "ff-pictures")
    @ToString.Exclude
    private FireForest fireForest;

}
