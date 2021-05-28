package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.PatternDao;
import com.diplom.diplomspringboot.models.entity.Pattern;
import com.diplom.diplomspringboot.service.abstracts.PatternService;
import org.springframework.stereotype.Service;

@Service
public class PatternServiceImpl extends ReadWriteServiceImpl<Long, Pattern> implements PatternService {

    private final PatternDao dao;

    public PatternServiceImpl(PatternDao dao) {
        super(dao);
        this.dao = dao;
    }
}
