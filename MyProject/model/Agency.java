package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agencyEmail")
@Entity
@Table(name = "agency")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Agency {
    @Id
    @Column(name = "agency_email", length = 100)
    private String agencyEmail;
    @Column(length = 50)
    private String agencyName;
    @Column(length = 50)
    private String agencyPassword;

    @OneToMany(mappedBy = "agencyEmail")
    @JsonIgnoreProperties("agencyEmail")
    private List<Recruit_Form> recruit_form;

    @OneToMany(mappedBy = "agency")
    @JsonIgnoreProperties("agency-report")
    private List<Fire_Forest_Detail> reportFireForest;
}
