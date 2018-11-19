package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.model.AppUser;
import com.a1704471.lookingforgamer.model.SignupForm;
import com.a1704471.lookingforgamer.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web")
public class UserWebController {

    @Autowired
    BCryptPasswordEncoder bencoder;

    @Autowired
    AppUserRepository userRepo;

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("appUser") AppUser user){

        user.setPassword(bencoder.encode(user.getPassword()));
        System.out.println("Saving user: "+user.getUsername()+" , with password: "+user.getPassword());

        userRepo.save(user);

        return "redirect:/web/login";
    }

    @RequestMapping(value = "signup")
    public String addUser(Model model){
        model.addAttribute("appUser", new AppUser());
        return "signup";
    }

    @RequestMapping(value="/login")
    public String login(){
        System.out.println("Logging in!!!");
        return "customLogin";
    }

//     @RequestMapping(value = "/shitsaveuser", method = RequestMethod.POST)
//    public String save(@Valid @ModelAttribute("signupform") AppUser user, BindingResult bindingResult){
//        if(!bindingResult.hasErrors()){
//            String pwd = user.getPassword();
//            String hashPwd = bencoder.encode(pwd);
//
//            AppUser newUser = new AppUser();
//            newUser.setPassword(hashPwd);
//            newUser.setUsername(user.getUsername());
//
//            //for easy creation of admin user
//
//            if(userRepo.findByUsername(user.getUsername()) == null) {
//                System.out.println("Creating user with user name: "+newUser.getUsername());
//                userRepo.save(newUser);
//            } else {
//                bindingResult.rejectValue("username", "err.username", "Username already exists!");
//                return "signup";
//            }
//        } else {
//            bindingResult.rejectValue("passwordCheck","err.passCheck", "Password does not match");
//            return "signup";
//        }
//        return "redirect:/login";
//    }
}
