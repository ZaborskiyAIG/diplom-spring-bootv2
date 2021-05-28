package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.ScaleDao;
import com.diplom.diplomspringboot.models.entity.Scale;
import org.springframework.stereotype.Repository;

@Repository
public class ScaleServiceDaoImpl extends ReadWriteDaoImpl<Long, Scale> implements ScaleDao {
}
