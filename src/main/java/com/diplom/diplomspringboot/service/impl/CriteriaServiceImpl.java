package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.CriteriaDao;
import com.diplom.diplomspringboot.models.entity.Criteria;
import com.diplom.diplomspringboot.service.abstracts.CriteriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaServiceImpl extends ReadWriteServiceImpl<Long, Criteria> implements CriteriaService {

    private final CriteriaDao criteriaDao;

    public CriteriaServiceImpl(CriteriaDao criteriaDao) {
        super(criteriaDao);
        this.criteriaDao = criteriaDao;
    }

    @Override
    public List<Criteria> getAllByPatternId(Long id) {
        return criteriaDao.getAllByPatternId(id);
    }
}
