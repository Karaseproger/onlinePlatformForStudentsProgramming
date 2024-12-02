package com.shop.shop.polylab.repoziory;

import com.shop.shop.polylab.models.RegisterDto;
import com.shop.shop.polylab.models.postSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<postSecurity, Integer> {
    public postSecurity findByEmail(String email);
}
