package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRoleByNameAndUserId(String roleName, Long userId) {
        try {
            Query query = entityManager.createQuery("from Role where role = :roleName and user_id = :userId");
            query.setParameter("roleName", roleName);
            query.setParameter("userId", userId);
            return (Role) query.getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            return null;
        }
    }


    @Override
    public void deleteRole(String roleName, Long userId) {
        entityManager.remove(findRoleByNameAndUserId(roleName, userId));
    }

}
