package com.a1704471.lookingforgamer.web;

import com.a1704471.lookingforgamer.domain.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PosterController {
    @Autowired
    private PosterRepository postRepo;

    @RequestMapping(value="/")
    public String indexPage(Model model){
        model.addAttribute("posters", postRepo.findAll());
        return "index";
    }
}
