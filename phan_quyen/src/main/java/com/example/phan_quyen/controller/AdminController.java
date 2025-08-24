package com.example.phan_quyen.controller;

import com.example.phan_quyen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        // Lấy danh sách tất cả user
        var users = userService.getAllUsers(); // ✅ lưu vào biến trước
        model.addAttribute("users", users);
        System.out.println("Số lượng user: " + users.size());
        return "admin"; // admin.html trong templates
    }

}
