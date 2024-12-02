package com.shop.shop.polylab.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/").setViewName("users");
        registry.addViewController("/zadan").setViewName("zadan");
        registry.addViewController("/videos").setViewName("videos");
        registry.addViewController("/").setViewName("users");
        registry.addViewController("/login").setViewName("login");
    }
}
