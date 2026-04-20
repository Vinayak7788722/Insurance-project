package com.jspider.insurance_policy_management.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.insurance_policy_management.dto.LoginRequest;
import com.jspider.insurance_policy_management.dto.LoginResponse;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	private final UserRepository repo;
	
	private final PasswordEncoder encoder;
	
	public AuthController(UserRepository repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		return repo.save(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

	    Optional<User> optionalUser = repo.findByEmail(request.getEmail());

	    if (optionalUser.isEmpty()) {
	        return ResponseEntity.status(401).body("Invalid email");
	    }

	    User user = optionalUser.get();

	    if (!encoder.matches(request.getPassword(), user.getPassword())) {
	        return ResponseEntity.status(401).body("Invalid password");
	    }

	    // ✅ Return email + password for Basic Auth usage
	    return ResponseEntity.ok(new LoginResponse(
	            user.getId(),
	            user.getName(),
	            user.getEmail(),
	            user.getRole(),
	            "SUCCESS"
	    ));
	}
}
