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
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "recruitId")
@Entity
@Table(name="recruit_form")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Recruit_Form {
    @Id
    @GeneratedValue
    @Column(name = "recruit_id",length = 10)
    private int recruitId;

    private Date startDate;

    private Date endDate;
    @Column(length = 3)
    private int max;

    private String description;

    private String recruitLocation;

    @ManyToOne
    @JoinColumn(name = "agency_email")
    @JsonIgnoreProperties("recruitId")
    @JsonBackReference
    private Agency agencyEmail;

    @OneToMany(mappedBy = "recruit_form")
    @JsonIgnoreProperties("recruitId")
    private List<Apply_Form> applyForm;

}
