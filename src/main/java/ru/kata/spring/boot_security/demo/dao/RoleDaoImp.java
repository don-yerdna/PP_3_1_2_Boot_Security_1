package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(String roleName) {
        return entityManager.find(Role.class, roleName);
    }

    @Override
    public void deleteRole(String roleName) {
        entityManager.remove(entityManager.find(Role.class, roleName));
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }
}
