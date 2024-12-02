package com.shop.shop.polylab.services;


import com.shop.shop.polylab.models.postSecurity;
import com.shop.shop.polylab.repoziory.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServise implements UserDetailsService {

    @Autowired
    private Users repo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        postSecurity appUser = repo.findByEmail(email);

        if (appUser != null){
            var springUser = User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole())
                    .build();
            return springUser;
        }
        return null;
    }
}
