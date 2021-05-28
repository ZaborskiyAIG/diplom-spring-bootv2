package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.PatternDao;
import com.diplom.diplomspringboot.models.entity.Pattern;
import org.springframework.stereotype.Repository;

@Repository
public class PatternDaoImpl extends ReadWriteDaoImpl<Long, Pattern> implements PatternDao {
}
