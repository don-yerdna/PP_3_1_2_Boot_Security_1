package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
    void addRole(Role role);

    Role findRoleByNameAndUserId(String roleName, Long userId);

    void deleteRole(String roleName, Long userId);

}
