package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.entity.Criteria;

import java.util.List;

public interface CriteriaDao extends ReadWriteDao<Long, Criteria> {

    List<Criteria> getAllByPatternId(Long id);

    List<Criteria> getCriteriaByProjectId(Long id);

}
