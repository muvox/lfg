package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.model.AppUser;
import com.a1704471.lookingforgamer.model.SignupForm;
import com.a1704471.lookingforgamer.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        userRepo.save(user);
    }




}