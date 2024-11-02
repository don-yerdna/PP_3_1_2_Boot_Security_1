package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
    void addRole(Role role);
    Role getRole(String roleName);
    void deleteRole(String roleName);
    void updateRole(Role role);
}
