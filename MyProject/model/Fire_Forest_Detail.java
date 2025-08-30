package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fireForestId")
@Entity
@Table(name = "fire_forest_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Fire_Forest_Detail {
    @Id
    @Column(name = "fire_forest_id", length = 10)
    private int fireForestId;
    @Column(length = 50)
    private String fireStatus;
    @Column(length = 50)
    private String assessDamage;
    private String summarize;
    private int amount;

    @OneToOne
    @MapsId
    @JoinColumn(name = "fire_forest_id")
    @JsonBackReference(value = "ff-detail")
    @ToString.Exclude
    private FireForest fireForest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("fireForestId")
    @JsonBackReference
    @JoinColumn(name = "volunteer_email")
    private JoinMember joinMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("fireForestId")
    @JsonBackReference(value = "agency-report")
    @JoinColumn(name = "agency_email")
    private Agency agency;

}
