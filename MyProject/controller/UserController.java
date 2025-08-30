package com.example.MyProject.controller;

import com.example.MyProject.model.FireForest;
import com.example.MyProject.model.User;
import com.example.MyProject.repo.UserRepo;
import com.example.MyProject.service.UserService;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://10.0.2.2:8080")//dont forget to add this
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepo repo;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = repo.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody User user){
        //save the user
        System.out.println("ได้รับข้อมูล: " + user);
        String msg = service.saveUser(user);

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/listuser")
    private List<User> getAllUser(){
        return  repo.findAll();
    }



    @PutMapping("/edit/{id}")
    private User editUser(@PathVariable String id, @Validated @RequestBody User user){
    User userToUpdate = repo.findById(id).orElse(null);

    if(userToUpdate == null){
        throw new IllegalArgumentException("ไม่พบข้อมูล");
    }
    userToUpdate.setUserFname(user.getUserFname());
    userToUpdate.setUserLname(user.getUserLname());
    userToUpdate.setUserGender(user.getUserGender());
    userToUpdate.setUserAddress(user.getUserAddress());
    userToUpdate.setUserPassword(user.getUserPassword());
    userToUpdate.setUserTel(user.getUserTel());
    return repo.save(userToUpdate);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        System.out.println("ได้รับข้อมูลที่ต้องการลบ: " + id);
        String msg = service.deleteUserById(id);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        return repo.findByUserEmailAndUserPassword(email, password)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(Map.of("message", "Login success", "user", user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }





}
