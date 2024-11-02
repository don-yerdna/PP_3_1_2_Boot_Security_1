package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-edit";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() != 0) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/admin";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam(value = "id", required = true) Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
