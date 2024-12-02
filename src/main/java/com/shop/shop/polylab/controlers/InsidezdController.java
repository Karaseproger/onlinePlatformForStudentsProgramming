package com.shop.shop.polylab.controlers;


import com.shop.shop.polylab.models.post;
import com.shop.shop.polylab.models.postInside;
import com.shop.shop.polylab.repoziory.PostInsideRep;
import com.shop.shop.polylab.repoziory.PostRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class InsidezdController {
    @Autowired
    private PostInsideRep postInsideRep;
    @GetMapping("/insidezd")
    public String insite(Model model  ){
        return ("insidezd");

    }
    @PostMapping("/insidezd")
    public String insite(@RequestParam String title, @RequestParam String date, @RequestParam String full_text, Model model){
        postInside posts = new postInside(title, date, full_text);
        postInsideRep.save(posts);
        return "redirect:/insidezd";

    }



    @GetMapping("/admininsidezd")
    public String insiteadmin(Model model){
        Iterable<postInside> postInsides = postInsideRep.findAll();
        model.addAttribute("postinside", postInsides);
        return "adminInside";
    }

    @GetMapping("/insidezd/{id}")
    public String insidezdDetalise(@PathVariable(value = "id") long id, Model model){
        if(!postInsideRep.existsById(id)){
            return "redirect:/blog";
        }
        Optional<postInside> postInside = postInsideRep.findById(id);
        ArrayList <postInside> res = new ArrayList<>();
        postInside.ifPresent(res::add);
        model.addAttribute("postinside", res);
        return "insiddezd_detalis";
    }

    @PostMapping("/insidezd/{id}/edit")
    public String insideEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String date,@RequestParam String full_text,   Model model){
        return null;
    }
}
