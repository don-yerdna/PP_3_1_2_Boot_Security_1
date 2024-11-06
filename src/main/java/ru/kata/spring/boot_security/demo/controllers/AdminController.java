package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "user-add";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {

            userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/remove")
    public String removeUser(@RequestParam(value = "id", required = true) Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

//    @GetMapping("/role/removeAdminRole")
//    public String removeAdminRole(@RequestParam(value = "id", required = true) Long id, Model model) {
//        userService.removeRoleByUserId(id, "ROLE_ADMIN");
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "user-edit";
//    }
//
//    @GetMapping("/role/removeUserRole")
//    public String removeUserRole(@RequestParam(value = "id", required = true) Long id, Model model) {
//        userService.removeRoleByUserId(id, "ROLE_USER");
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "user-edit";
//    }
//
//    @GetMapping("/role/addAdminRole")
//    public String addAdminRole(@RequestParam(value = "id", required = true) Long id, Model model) {
//        userService.addRoleByUserId(id, "ROLE_ADMIN");
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "user-edit";
//    }
//
//    @GetMapping("/role/addUserRole")
//    public String addUserRole(@RequestParam(value = "id", required = true) Long id, Model model) {
//        userService.addRoleByUserId(id, "ROLE_USER");
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "user-edit";
//    }
}
