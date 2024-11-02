package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    void addRole(Role role);
    Role getRole(String roleName);
    void deleteRole(String roleName);
    void updateRole(Role role);
}
