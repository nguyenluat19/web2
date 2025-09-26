package com.web2.webbt.controller;

import com.web2.webbt.dto.RegisterRequest;
import com.web2.webbt.model.Company;
import com.web2.webbt.model.User;
import com.web2.webbt.repository.CompanyRepository;
import com.web2.webbt.repository.UserRepository;
import com.web2.webbt.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req){
        if(userRepository.existsByUsername(req.getUsername()))
            return ResponseEntity.badRequest().body("Username already taken");

        Company c = null;
        if(req.getCompanyId() != null){
            c = companyRepository.findById(req.getCompanyId()).orElse(null);
        }

        User u = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .fullName(req.getFullName())
                .email(req.getEmail())
                .role(req.getRole() != null ? req.getRole() : "ROLE_USER")
                .company(c)
                .build();

        userRepository.save(u);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.get("username"), req.get("password"))
        );
        User user = userRepository.findByUsername(req.get("username")).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(),user.getRole());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", user.getUsername(),
                "role", user.getRole()
        ));
    }
}
