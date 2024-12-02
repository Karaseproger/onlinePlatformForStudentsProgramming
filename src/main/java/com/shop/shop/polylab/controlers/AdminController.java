package com.shop.shop.polylab.controlers;


import com.shop.shop.polylab.models.post;
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
public class AdminController    {
    @Autowired
        private PostRepozitory postRepozitory;

    @GetMapping("/zadan")
    public String zadan(Model model){
        Iterable<post> post = postRepozitory.findAll();
        model.addAttribute("post", post);
        return "zadan";
    }

    @GetMapping("/zadan/add")
    public String zadanAdd(Model model){
        return "zadan_add";
    }

    @PostMapping("/zadan/add")
    public String zdanPostAdd(@RequestParam String title, @RequestParam String date, @RequestParam String full_text, Model model){
        post post = new post(title, date, full_text);
        postRepozitory.save(post);
        return "redirect:/zadan";
    }


    @GetMapping("/zadan/{id}")
    public String zadanDetales(@PathVariable(value = "id") long id, Model model){
        if(!postRepozitory.existsById(id)){
            return "redirect:/blog";
        }
        Optional<post> post = postRepozitory.findById(id);
        ArrayList<post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "zadan_detalis";
    }

    @PostMapping("/zadan/{id}/edit")
    public String zdanEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String date,@RequestParam String full_text,   Model model){
        post post = postRepozitory.findById(id).orElseThrow();
        post.setTitle(title);
        post.setDate(date);
        post.setFull_text(full_text);
        postRepozitory.save(post);
        return "redrect:/zadan";
    }







}
