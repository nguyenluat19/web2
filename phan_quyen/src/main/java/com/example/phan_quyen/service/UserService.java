package com.example.phan_quyen.service;

import com.example.phan_quyen.model.User;
import com.example.phan_quyen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Đăng ký user mới (mặc định ROLE_USER)
    public User register(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username đã tồn tại");
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles(Set.of("ROLE_USER"))
                .enabled(true)
                .build();
        return userRepository.save(user);
    }

    // Lấy tất cả user (chỉ ADMIN mới gọi trong controller)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Tạo admin (sử dụng khi khởi tạo hệ thống hoặc thêm admin mới)
    public User createAdmin(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username đã tồn tại");
        }
        User admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles(Set.of("ROLE_ADMIN"))
                .enabled(true)
                .build();
        return userRepository.save(admin);
    }
}
