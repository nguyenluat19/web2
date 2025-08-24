package com.example.phan_quyen.service;

import com.example.phan_quyen.model.User;
import com.example.phan_quyen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user: " + username));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))
                .disabled(!u.isEnabled())
                .build();
    }
}
