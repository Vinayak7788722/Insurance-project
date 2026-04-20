package com.jspider.insurance_policy_management.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ IMPORTANT: Add ROLE_ prefix for @PreAuthorize("hasRole()") to work
        return new org.springframework.security.core.userdetails.User(
        	    user.getEmail(),
        	    user.getPassword(),
        	    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        	);
    }
}