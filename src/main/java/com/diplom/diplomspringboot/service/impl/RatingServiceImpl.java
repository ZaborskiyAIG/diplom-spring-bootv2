package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.DemandDtoDao;
import com.diplom.diplomspringboot.dao.abstracts.RatingDao;
import com.diplom.diplomspringboot.models.dto.CriteriaRankDto;
import com.diplom.diplomspringboot.models.dto.RatingDto;
import com.diplom.diplomspringboot.models.entity.Demands;
import com.diplom.diplomspringboot.models.entity.Rating;
import com.diplom.diplomspringboot.service.abstracts.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl extends ReadWriteServiceImpl<Long, Rating> implements RatingService {

    private final RatingDao dao;
    private final DemandDtoDao demandDtoDao;

    public RatingServiceImpl(RatingDao dao, DemandDtoDao demandDtoDao) {
        super(dao);
        this.dao = dao;
        this.demandDtoDao = demandDtoDao;
    }

    @Override
    @Transactional
    public void deleteRatingByCriteriaId(Long id) {
        dao.deleteRatingByCriteriaId(id);
    }

    @Override
    @Transactional
    public void deleteRatingByDemandId(Long id) {
        dao.deleteRatingByDemandId(id);
    }

    @Override
    public List<RatingDto> getAllRatingByPatternId(Long id, Long projectId) {

        List<Demands> demands = demandDtoDao.getAllByActivityFilialAndByProjectId(projectId);
        demands.addAll(demandDtoDao.getAllByActivityAndByProjectId(projectId));


        List<RatingDto> ratingDtoList = new ArrayList<>();

        //TODO без комментариев
        for(Demands o: demands) {

            if (o.getName() == null) {
                continue;
            }

            List<Rating> ratings = dao.getAllRatingByDemandId(o.getId(), id);
            List<CriteriaRankDto> criteria = new ArrayList<>();

            for(Rating r: ratings) {
                CriteriaRankDto dto = new CriteriaRankDto();
                dto.setId(r.getId());
                dto.setAbbreviation(r.getCriteria().getAbbreviation());
                dto.setMark(r.getMark());
                dto.setNameCriteria(r.getCriteria().getName());
                criteria.add(dto);
            }

            RatingDto dtos = new RatingDto();
            dtos.setCriteria(criteria);
            dtos.setId(o.getId());
            dtos.setNameDemand(o.getName());

            ratingDtoList.add(dtos);
        }

        return ratingDtoList;
    }
}
