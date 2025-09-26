package com.web2.webbt.service;

import com.web2.webbt.dto.RegisterRequest;
import com.web2.webbt.model.Company;
import com.web2.webbt.model.User;
import com.web2.webbt.repository.CompanyRepository;
import com.web2.webbt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        Company c = null;
        if (req.getCompanyId() != null) {
            c = companyRepository.findById(req.getCompanyId()).orElse(null);
        }
        User u = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .fullName(req.getFullName())
                .email(req.getEmail())
                .role("ROLE_USER")
                .company(c)
                .build();
        return userRepository.save(u);
    }
}
