package com.a1704471.lookingforgamer.web;

import com.a1704471.lookingforgamer.domain.Poster;
import com.a1704471.lookingforgamer.domain.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PosterController {
    @Autowired
    private PosterRepository postRepo;

    @RequestMapping(value="/posters")
    public String indexPage(Model model){
        model.addAttribute("posters", postRepo.findAll());
        return "posters";
    }

    @RequestMapping(value= "/add")
    public String addBook(Model model){
        model.addAttribute("poster", new Poster());
        model.addAttribute("categories", postRepo.findAll());
        return "addposter";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Poster poster){
        postRepo.save(poster);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePoster(@PathVariable("id") Long posterId, Model model){
        postRepo.deleteById(posterId);
        return "redirect:../";
    }
}
