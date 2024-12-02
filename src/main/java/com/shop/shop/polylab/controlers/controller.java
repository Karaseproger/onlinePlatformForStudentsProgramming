package com.shop.shop.polylab.controlers;


import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }


    @GetMapping("/users")
    public String user(Model model){
        return "users";
    }



    @GetMapping("/login")
    public String login(Model model){
        return "login";
        
    }


    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }


}
