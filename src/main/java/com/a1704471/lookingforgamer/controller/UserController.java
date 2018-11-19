package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.model.AppUser;
import com.a1704471.lookingforgamer.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public void signUp(@RequestBody AppUser user){
        user.setPassword(bencoder.encode(user.getPassword()));
        System.out.println("Saving user: "+user.getUsername()+" , with password: "+user.getPassword());

        userRepo.save(user);
    }

}