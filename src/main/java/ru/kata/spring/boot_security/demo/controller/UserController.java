package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/edit")
    public String editUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() != 0) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/";
    }

    @RequestMapping("/remove")
    public String removeUser(@RequestParam(value = "id", required = true) Long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
