package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.CriteriaDao;
import com.diplom.diplomspringboot.models.entity.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CriteriaDaoImpl extends ReadWriteDaoImpl<Long, Criteria> implements CriteriaDao {


    @Override
    public List<Criteria> getAllByPatternId(Long id) {
        return entityManager.createQuery("SELECT criteria FROM Criteria criteria WHERE criteria.pattern.id = :id", Criteria.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Criteria> getCriteriaByProjectId(Long id) {
        return entityManager.createNativeQuery("SELECT * FROM criteria WHERE criteria.pattern_id IN (SELECT project_pattern.pattern_id FROM project_pattern " +
                "WHERE project_pattern.project_id = :projectId)", Criteria.class)
                .setParameter("projectId", id)
                .getResultList();
    }
}
