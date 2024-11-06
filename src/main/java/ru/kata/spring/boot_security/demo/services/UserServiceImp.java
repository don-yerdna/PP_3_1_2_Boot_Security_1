package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
//        if (!user.getPassword().equals("")) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        } else {
//            user.setPassword(userRepository.findById(user.getId())
//                    userDao.getUserById(user.getId()).getPassword());
//        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
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
