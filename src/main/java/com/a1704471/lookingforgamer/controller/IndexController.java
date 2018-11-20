package com.a1704471.lookingforgamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value="")
    public String redirectToWeb(){
        return "redirect:/web/";
    }
}
