package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.UserDao;
import com.diplom.diplomspringboot.models.entity.User;
import com.diplom.diplomspringboot.service.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<Long, User> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

}
