package ru.kata.spring.boot_security.demo.dao;

import javax.persistence.*;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void addUser(User user) {
        System.out.println(user);
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUserById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User findUserByUsername(String username) {
        System.out.println("DAO "+  username);//получен String вместо Long
        try {
            Query query = entityManager.createQuery("from User where username = :username");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NullPointerException | NoResultException e){
            return null;
        }


//        return entityManager.find(User.class, username);
//        return entityManager.createQuery(" from User u where u.username = :uname",User.class)
//                .setParameter("uname", username).getSingleResult();
//



    }
}
