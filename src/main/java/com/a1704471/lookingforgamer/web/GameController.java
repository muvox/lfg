package com.a1704471.lookingforgamer.web;

import com.a1704471.lookingforgamer.IGDBAccess;
import com.a1704471.lookingforgamer.domain.Game;
import com.a1704471.lookingforgamer.domain.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameController {
//    @Autowired
//    IGDBAccess repo;

    @Autowired
    GameRepository repo;

    @RequestMapping(value="/")
    public String indexPage(Model model){
        // ei näin joni, hyi hyi
        model.addAttribute("games", repo.findAll());
        return "index";
    }
}
