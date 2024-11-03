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
    public UserServiceImp(UserDao userDAO, RoleDao roleDao) {
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
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(userDAO.getUserById(user.getId()).getPassword());
        }
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        role.setUser(user);
        roles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
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
        User user = userDAO.getUserById(id);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            roleDao.deleteRole(role.getRole(), id);
        }
        userDAO.removeUserById(id);
    }

    @Transactional
    @Override
    public void removeRoleByUserId(Long id, String userRole) {
        User user = userDAO.getUserById(id);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getRole().equals(userRole)) {
                roles.remove(role);
                roleDao.deleteRole(role.getRole(), id);
                break;
            }
        }
        user.setRoles(roles);

        userDAO.updateUser(user);


    }

    @Transactional
    @Override
    public void addRoleByUserId(Long id, String userRole) {
        User user = userDAO.getUserById(id);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getRole().equals(userRole)) {
                return;
            }
        }
        Role role = new Role();
        role.setRole(userRole);
        role.setUser(user);
        roles.add(role);
        user.setRoles(roles);
        roleDao.addRole(role);
        userDAO.updateUser(user);
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

}
