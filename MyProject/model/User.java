package com.example.MyProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userEmail")
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User {
    @Id
    @Column(name = "user_email",length = 100)
    private String userEmail;
    @Column(length = 50,nullable = false)
    private String userFname;
    @Column(length = 50,nullable = false)
    private String userLname;
    @Column(length = 10,nullable = false)
    private String userGender;
    @Column(nullable = false)
    private Date userBirthDay;

    private String userAddress;
    @Column(length = 50,nullable = false)
    private String userPassword;
    @Column(length = 10)
    private String userTel;

    @OneToMany(mappedBy = "userEmail", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-fireforests")
    @ToString.Exclude
    private List<FireForest> fireforestId;

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties(value = "userEmail-volunteer")
    private Volunteer volunteer;


}
