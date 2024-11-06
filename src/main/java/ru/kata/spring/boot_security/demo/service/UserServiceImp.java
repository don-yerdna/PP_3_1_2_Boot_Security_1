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

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao,  PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(userDao.getUserById(user.getId()).getPassword());
        }
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

//    @Transactional
//    @Override
//    public void removeRoleByUserId(Long id, String userRole) {
//        User user = userDao.getUserById(id);
//        Set<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            if (role.getRole().equals(userRole)) {
//                roles.remove(role);
//                roleDao.deleteRole(role.getRole(), id);
//                break;
//            }
//        }
//        user.setRoles(roles);
//
//        userDao.updateUser(user);
//
//
//    }

//    @Transactional
//    @Override
//    public void addRoleByUserId(Long id, String userRole) {
//        User user = userDao.getUserById(id);
//        Set<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            if (role.getRole().equals(userRole)) {
//                return;
//            }
//        }
//        Role role = new Role();
//        role.setRole(userRole);
////        role.setUser(user);
//        roles.add(role);
//        user.setRoles(roles);
//        roleDao.save(role);
//        userDao.updateUser(user);
//    }


}
