package com.a1704471.lookingforgamer.controller;

import com.a1704471.lookingforgamer.model.Game;
import com.a1704471.lookingforgamer.model.Poster;
import com.a1704471.lookingforgamer.repository.GameRepository;
import com.a1704471.lookingforgamer.repository.PosterRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private PosterRepository postRepo;
    private GameRepository gameRepo;

    public ApiController(PosterRepository postRepo, GameRepository gameRepo){
        this.postRepo = postRepo;
        this.gameRepo = gameRepo;
    }

    @PostMapping
    public void addPoster(@RequestBody Poster poster){
        postRepo.save(poster);
    }

    @GetMapping("/games")
    public List<Game> getGames(){
        return (List<Game>) gameRepo.findAll();
    }

    @GetMapping("/posters/{id}")
    public List<Poster> getPosters(@PathVariable("id") Long gameId){
        return postRepo.findByGameId(gameId);
    }

    @PutMapping("/{id}")
    public void editPoster(@PathVariable long id, @RequestBody Poster poster){
        Poster existingPoster = postRepo.findById(id).get();
        Assert.notNull(existingPoster, "Poster not found");
        existingPoster.setDetails(poster.getDetails());
        postRepo.save(existingPoster);
    }

    @DeleteMapping("/{id}")
    public void deletePoster(@PathVariable long id){
        Poster posterToDelete = postRepo.findById(id).get();
        postRepo.delete(posterToDelete);
    }

}
