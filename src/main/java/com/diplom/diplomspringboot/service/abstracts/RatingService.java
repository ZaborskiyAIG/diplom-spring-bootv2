package com.diplom.diplomspringboot.service.abstracts;

import com.diplom.diplomspringboot.models.dto.RatingDto;
import com.diplom.diplomspringboot.models.entity.Rating;

import java.util.List;

public interface RatingService extends ReadWriteService<Long, Rating> {

    void deleteRatingByCriteriaId(Long id);

    void deleteRatingByDemandId(Long id);

    List<RatingDto> getAllRatingByPatternId(Long id, Long projectId);

}
