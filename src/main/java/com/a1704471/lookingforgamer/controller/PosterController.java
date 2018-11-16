package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.misc.IGDBAccess;
import com.a1704471.lookingforgamer.model.Game;
import com.a1704471.lookingforgamer.repository.GameRepository;
import com.a1704471.lookingforgamer.model.Poster;
import com.a1704471.lookingforgamer.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class PosterController {

    @Autowired
    private PosterRepository postRepo;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    IGDBAccess gameApi;

    @RequestMapping(value="/posters")
    public String indexPage(Model model){
        model.addAttribute("posters", postRepo.findAll());
        return "posters";
    }

    @RequestMapping(value="fetchPage")
    public String posterFetch(){
        gameApi.getPCGames();

        return "fetchPage";
        }

    @RequestMapping(value= "/add{id}", method = RequestMethod.GET)
    public String addPoster(@PathVariable("id") Long gameId, Model model){
        List<Game> gamesList = gameRepo.findGamesById(gameId);
        Poster uusPoster = new Poster();
        uusPoster.setGameId(gameId);
        uusPoster.setGameName(gameRepo.findGameById(gameId).getName());
        model.addAttribute("poster", uusPoster);
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameName", gameRepo.findGameById(gameId).getName());
        return "addposter";
    }

    @PostMapping(value="/save")
    public String save(Poster poster){
        Date date = new Date();
        poster.setDate(date);
        postRepo.save(poster);

        return "redirect:posters";
    }

    @RequestMapping(value="/posters/{id}", method = RequestMethod.GET)
    public String postersByGame(@PathVariable("id") Long gameId,  Model model){
        model.addAttribute("posters", postRepo.findByGameId(gameId));
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameName", gameRepo.findGameById(gameId).getName());
        return "posters";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePoster(@PathVariable("id") Long posterId, Model model){
        postRepo.deleteById(posterId);
        return "redirect:../posters";
    }
}
