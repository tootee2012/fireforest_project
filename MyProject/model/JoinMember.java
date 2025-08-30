package com.example.MyProject.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userEmail")
@Entity
@Table(name = "join_member")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class JoinMember {
    @Id
    @Column(name = "volunteer_email", length = 100)
    private String userEmail;

    private String work_status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "volunteer_email")
    @JsonIgnoreProperties(value = "userEmail-join")
    private Volunteer volunteer;

    @OneToMany(mappedBy = "joinMember")
    @JsonIgnoreProperties("userEmail")
    private List<Fire_Forest_Detail> reportFireForest;

}
