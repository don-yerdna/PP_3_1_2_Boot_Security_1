package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;
@Component
public class InitApp {
    private final RoleService roleService;
    private final UserService userService;

    public InitApp(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

//    private final UserDao userRepo;
//    private final RoleDao roleRepo;
//
//    public InitApp(UserDao userRepo, RoleDao roleRepo) {
//        this.userRepo = userRepo;
//        this.roleRepo = roleRepo;
//    }

    @PostConstruct
    public void init(){
        Role roleAdmin = new Role();
        roleAdmin.setRole("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@admin.com");
        admin.setAge(22);
        admin.setRoles(Set.of(roleAdmin));

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setFirstName("user");
        user.setLastName("user");
        user.setEmail("user@user.com");
        user.setAge(22);
        user.setRoles(Set.of(roleUser));

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setFirstName("user1");
        user1.setLastName("user1");
        user1.setEmail("user1@user.com");
        user1.setAge(22);
        user1.setRoles(Set.of(roleUser));

//        User user2 = new User();
//        user2.setUsername("user2");
//        user2.setPassword("password2");
//        user2.setFirstName("user2");
//        user2.setLastName("user2");
//        user2.setEmail("user2@user.com");
//        user2.setAge(22);
//        user2.setRoles(Set.of(roleUser, roleAdmin));
        userService.addUser(admin);
        userService.addUser(user);
//        userService.addUser(user1);
//        userService.addUser(user2);
    }

}
