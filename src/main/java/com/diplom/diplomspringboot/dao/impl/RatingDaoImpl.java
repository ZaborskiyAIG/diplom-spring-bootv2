package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.RatingDao;
import com.diplom.diplomspringboot.models.entity.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDaoImpl extends ReadWriteDaoImpl<Long, Rating> implements RatingDao {

    @Override
    public void deleteRatingByCriteriaId(Long id) {
        entityManager.flush();
        entityManager.clear();
        entityManager.createQuery("DELETE FROM Rating rating WHERE  rating.criteria.id = :Id")
                .setParameter("Id", id)
                .executeUpdate();
    }

    @Override
    public List<Rating> getAllRatingByPatternId(Long id) {
        return entityManager.createQuery("SELECT rating FROM Rating rating where rating.criteria.pattern.id = :id order by rating.demands.name", Rating.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Rating> getAllRatingByDemandId(Long id, Long patternId) {
        return entityManager.createQuery("SELECT rating FROM Rating rating where rating.demands.id = :id and rating.criteria.pattern.id = :patternId", Rating.class)
                .setParameter("id", id)
                .setParameter("patternId", patternId)
                .getResultList();
    }

    @Override
    public void deleteRatingByDemandId(Long id) {
        entityManager.flush();
        entityManager.clear();
        entityManager.createQuery("DELETE FROM Rating rating WHERE  rating.demands.id = :Id")
                .setParameter("Id", id)
                .executeUpdate();
    }
}
