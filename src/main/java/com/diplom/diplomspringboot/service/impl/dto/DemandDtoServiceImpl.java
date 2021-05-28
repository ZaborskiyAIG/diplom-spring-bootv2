package com.diplom.diplomspringboot.service.impl.dto;

import com.diplom.diplomspringboot.dao.abstracts.ActivityDtoDao;
import com.diplom.diplomspringboot.dao.abstracts.CriteriaDao;
import com.diplom.diplomspringboot.dao.abstracts.DemandDtoDao;
import com.diplom.diplomspringboot.dao.abstracts.RatingDao;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import com.diplom.diplomspringboot.models.entity.Criteria;
import com.diplom.diplomspringboot.models.entity.Demands;
import com.diplom.diplomspringboot.models.entity.Rating;
import com.diplom.diplomspringboot.service.abstracts.dto.DemandDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemandDtoServiceImpl implements DemandDtoService {

    private final DemandDtoDao dao;
    private final ActivityDtoDao activityDtoDao;
    private final CriteriaDao criteriaDao;
    private final RatingDao ratingDao;

    public DemandDtoServiceImpl(DemandDtoDao dao, ActivityDtoDao activityDtoDao, CriteriaDao criteriaDao, RatingDao ratingDao) {
        this.dao = dao;
        this.activityDtoDao = activityDtoDao;
        this.criteriaDao = criteriaDao;
        this.ratingDao = ratingDao;
    }

    @Override
    @Transactional
    public void addDemand(Demands demands, Long id) {
        List<Criteria> criteria = criteriaDao.getCriteriaByProjectId(id);
        dao.addDemand(demands);
        criteria.forEach(value -> {
            Rating rating = new Rating();
            rating.setDemands(demands);
            rating.setCriteria(value);
            ratingDao.persist(rating);
        });
    }

    @Override
    @Transactional
    public void update(Demands demands, Integer predRank, Long newId) {
        if (dao.checkActivity(newId)) {
            ActivityFilial activityFilial = new ActivityFilial();
            activityFilial.setId(newId);
            demands.setActivityFilial(activityFilial);
            demands.setActivity(null);
        } else {
            Activity activity = new Activity();
            activity.setId(newId);
            demands.setActivity(activity);
            demands.setActivityFilial(null);
        }

        List<Demands> list = dao.get(newId);

        Long predId = dao.getActivityIdByDemandId(demands.getId());
        List<Demands> predList = dao.get(predId);

        for (int i = predRank; i < predList.size(); i++) {
            if (!predList.get(i).getId().equals(demands.getId())) {
                predList.get(i).setRank(predList.get(i).getRank() - 1);
            }
        }

        for (int i = demands.getRank(); i < list.size(); i++) {

            if (list.get(i).getId().equals(demands.getId())) { //нужно прикрутить предыдущий id в контроллер и тут его уже и сравнивать
                if (list.get(i).getRank().equals(demands.getRank()) && list.get(i).getActivity().getId().equals(predId)) {
                    return;
                }
                list.get(i).setRank(demands.getRank());
            } else {
                list.get(i).setRank(list.get(i).getRank()+1);
            }

        }
        dao.update(demands);
        List<Activity> activities = activityDtoDao.getAllActivity();
        List<ActivityFilial> activityFilials = dao.getActivityFilialIsNullDemand();

        for(Activity a: activities) {
            int count = 0;
            for(ActivityFilial af: activityFilials) {
                if(af.getActivity().getId().equals(a.getId())) {
                    count++;
                    if(count > 1) {
                        activityDtoDao.deleteActivityFilial(af.getId());
                    }
                }
            }

            if(count == 0) {
                ActivityFilial activityFilial = new ActivityFilial();
                activityFilial.setActivity(a);
                activityDtoDao.addActivityFilial(activityFilial);
            }
        }



    }

    @Override
    @Transactional
    public void update(Demands demands) {
        demands.setActivity(dao.find(demands.getId()).getActivity());
        dao.update(demands);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Demands demands = dao.find(id);
        dao.delete(demands);
    }

    @Override
    public List<Demands> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Demands> getAllByProjectId(Long id) {
        List<Demands> demands = dao.getAllByActivityAndByProjectId(id);
        demands.addAll(dao.getAllByActivityFilialAndByProjectId(id));
        return demands;
    }

    @Override
    public Demands getById(Long id) {
        return dao.find(id);
    }
}
