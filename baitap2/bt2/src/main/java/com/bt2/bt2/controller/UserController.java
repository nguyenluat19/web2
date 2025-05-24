package com.bt2.bt2.controller;

import com.bt2.bt2.model.User;
import com.bt2.bt2.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Hiển thị form thêm mới user
    @GetMapping("/users/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";  // Tên view form
    }

    // Xử lý submit form thêm mới user
    @PostMapping("/users")
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";  // Chuyển về trang danh sách
    }

    // Hiển thị danh sách user
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user_list";  // Tên view danh sách
    }
}
