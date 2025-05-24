package com.example.CategoryManager.Category;


import ch.qos.logback.core.model.Model;
import com.example.CategoryManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addText("users");
        return "users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        model.addText("users");
        model.addText("selectedUser");
        return "users";
    }

    @PostMapping
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }
}


