package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDAO;

    @Autowired
    public UserServiceImp(UserDao userDAO) {
        this.userDAO = userDAO;
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
}
