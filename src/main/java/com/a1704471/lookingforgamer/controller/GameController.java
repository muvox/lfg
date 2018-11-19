package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class GameController {
//    @Autowired
//    IGDBAccess repo;

    @Autowired
    GameRepository repo;

    @RequestMapping(value="")
    public String indexPage(Model model){
        // ei näin joni, hyi hyi
        model.addAttribute("games", repo.findAll());
        return "index";
    }
}
