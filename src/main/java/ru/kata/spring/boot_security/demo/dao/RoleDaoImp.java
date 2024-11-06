package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }

    //    @Transactional
    @Override
    public void save(Role role) {
        entityManager.merge(role);
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
