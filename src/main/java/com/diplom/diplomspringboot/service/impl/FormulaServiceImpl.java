package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.FormulaDao;
import com.diplom.diplomspringboot.models.entity.Formula;
import com.diplom.diplomspringboot.service.abstracts.FormulaService;
import org.springframework.stereotype.Service;

@Service
public class FormulaServiceImpl extends ReadWriteServiceImpl<Long, Formula> implements FormulaService {

    public FormulaServiceImpl(FormulaDao dao) {
        super(dao);
    }



}
