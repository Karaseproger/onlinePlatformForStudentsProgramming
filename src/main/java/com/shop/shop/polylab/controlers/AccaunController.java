package com.shop.shop.polylab.controlers;

import com.shop.shop.polylab.models.RegisterDto;


import com.shop.shop.polylab.models.postSecurity;
import com.shop.shop.polylab.repoziory.Users;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccaunController {
    @Autowired
    private Users repo;


    @GetMapping("/register")
    public String register(Model model){
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }


    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult result){
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            result.addError(
                    new FieldError("registerDto", "confirmPassword", "Password and Confirm Password do not match")
            );
        }
        postSecurity appUser= (postSecurity) repo.findByEmail(registerDto.getEmail());
        if (appUser != null){
            result.addError(
                    new FieldError("registerDto", "email", "Email address is already used")
            );
        }

        if(result.hasErrors()){
            return "register";
        }
        try{
            // создаю новый акаунт
            var bCryptEncoder = new BCryptPasswordEncoder();

            postSecurity newUser = new postSecurity();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("client");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);


        }
        catch (Exception ex){
            result.addError(
                    new FieldError("registerDto", "firstName"
                    , ex.getMessage())
            );
        }
        return "register";
    }
}
