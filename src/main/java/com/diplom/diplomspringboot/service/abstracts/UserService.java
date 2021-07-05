package com.diplom.diplomspringboot.service.abstracts;

import com.diplom.diplomspringboot.models.entity.User;

public interface UserService extends ReadWriteService<Long, User> {

    User getUserByLogin(String login);

}
