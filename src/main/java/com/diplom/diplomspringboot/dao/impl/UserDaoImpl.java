package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.UserDao;
import com.diplom.diplomspringboot.models.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<Long, User> implements UserDao {

    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery("SELECT user FROM User user join fetch user.role WHERE user.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }
}
