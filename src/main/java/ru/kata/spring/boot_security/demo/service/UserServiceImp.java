package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDAO;
    private final RoleDao roleDao;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImp(UserDao userDAO, RoleDao roleDao){
        this.userDAO = userDAO;
        this.roleDao = roleDao;

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        System.out.println(user);
//        System.out.println(new BCryptPasswordEncoder(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        role.setUser(user);
//        user.getRoles().add(role);

        roles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        System.out.println(user);
//        System.out.println(roles);
        roleDao.addRole(role);
        userDAO.addUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByUsername(username);
        System.out.println(user);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Username not found");
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userDAO.findUserByUsername(username);
////        System.out.println(user);
//        if(user!=null) {
//            return user;
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
}
