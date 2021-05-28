package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.entity.Rating;

import java.util.List;


public interface RatingDao extends ReadWriteDao<Long, Rating> {

    void deleteRatingByCriteriaId(Long id);

    List<Rating> getAllRatingByPatternId(Long id);

    List<Rating> getAllRatingByDemandId(Long id, Long patternId);

    void deleteRatingByDemandId(Long id);
}
