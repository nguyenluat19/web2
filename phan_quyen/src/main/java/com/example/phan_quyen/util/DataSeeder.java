package com.example.phan_quyen.util;

import com.example.phan_quyen.model.User;
import com.example.phan_quyen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(encoder.encode("admin123"))
                    .roles(Set.of("ROLE_ADMIN","ROLE_USER"))
                    .enabled(true)
                    .build();
            userRepo.save(admin);
        }
    }
}
