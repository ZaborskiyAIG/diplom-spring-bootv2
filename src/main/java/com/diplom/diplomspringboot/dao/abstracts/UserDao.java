package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.entity.User;

public interface UserDao extends ReadWriteDao<Long, User> {

    User getUserByLogin(String login);

}
