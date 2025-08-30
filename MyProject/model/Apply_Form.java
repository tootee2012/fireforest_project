package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "applyFormId")
@Entity
@Table(name="apply_form")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Apply_Form {

    @Id
    @GeneratedValue
    @Column(name = "apply_form_id")
    private int applyFormId;
    private String formDate;
    private String congenitalDiseases;
    private String allergicFood;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    @JsonIgnoreProperties("applyFormId")
    @JsonBackReference
    private Recruit_Form recruit_form;

    @OneToMany(mappedBy = "applyForm")
    @JsonIgnoreProperties(value = "volunteer-apply")
    private List<Volunteer> volunteer;
}
