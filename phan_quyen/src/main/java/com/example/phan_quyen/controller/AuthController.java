package com.example.phan_quyen.controller;

import com.example.phan_quyen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        try {
            userService.register(username, password);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
