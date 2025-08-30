package com.example.MyProject.service;

import com.example.MyProject.model.User;
import com.example.MyProject.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public String saveUser(User user){

        repo.save(user);
        return "User registered successfully";
    }

    public String deleteUserById(String id){
        System.out.println("ได้รับข้อมูลที่ทำการลบ: " + id);
        repo.deleteById(id);
        System.out.println("ลบข้อมูลเสร็จสิ้น");
        return "User deleted successfully";
    }



}
