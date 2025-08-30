package com.example.MyProject.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "experience_id")
@Entity
@Table(name = "experience")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Experience {
    @Id
    @GeneratedValue
    private int experience_id;
    private String experienceType;

    @OneToOne(mappedBy = "experience")
    private Volunteer volunteer;
}
