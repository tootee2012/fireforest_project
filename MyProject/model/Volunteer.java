package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userEmail")
@Entity
@Table(name = "volunteer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Volunteer {
    @Id
    @Column(name = "volunteer_email", length = 100)
    private String userEmail;
    @Column(length = 6)
    private double weight;
    @Column(length = 3)
    private double height;
    @Column(length = 50)
    private String talent;
    private boolean isTraining;
    private String applicationDate;
    private String entryDate;
    @Column(length = 20)
    private String volunteerStatus;

    private String volunteerLocation;
    private String volunteerLat;
    private String volunteerLong;
    @OneToOne
    @MapsId
    @JoinColumn(name = "volunteer_email", unique = true)
    private User user;

    @OneToOne(mappedBy = "volunteer")
    @JsonIgnoreProperties(value = "userEmail-join")
    private JoinMember joinMember;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    @JsonBackReference(value = "volunteer-apply")
    private Apply_Form applyForm;

    @OneToOne
    @JoinColumn(name = "experience_id")
    private Experience experience;

}
