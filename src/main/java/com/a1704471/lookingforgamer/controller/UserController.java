package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.model.AppUser;
import com.a1704471.lookingforgamer.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping("/users")
public class UserController {

    private AppUserRepository userRepo;
    private BCryptPasswordEncoder bencoder;

    public UserController(AppUserRepository userRepo, BCryptPasswordEncoder bencoder){
        this.userRepo = userRepo;
        this.bencoder = bencoder;
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody AppUser user){
        if(userRepo.findByUsername(user.getUsername())==null){
            user.setPassword(bencoder.encode(user.getPassword()));
            userRepo.save(user);
            System.out.println("Saving user: "+user.getUsername()+" , with password: "+user.getPassword());
            return "success";
        } else {
            System.out.println("error, username taken");
            return "Error: username taken!";
        }
    }
}