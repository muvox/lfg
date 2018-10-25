package com.a1704471.lookingforgamer.web;

import com.a1704471.lookingforgamer.IGDBAccess;
import com.a1704471.lookingforgamer.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GameController {
    @Autowired
    IGDBAccess repo;

    @RequestMapping(value="/")
    public String indexPage(Model model){
        List<Game> testilista = repo.startingGames();
        model.addAttribute("games", testilista);
        System.out.println(testilista.get(0).toString());
        return "index";
    }
}
