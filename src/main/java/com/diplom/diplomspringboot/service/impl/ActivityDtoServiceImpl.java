package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.ActivityDtoDao;
import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.service.abstracts.ActivityDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityDtoServiceImpl implements ActivityDtoService {

    private final ActivityDtoDao activityDtoDao;

    public ActivityDtoServiceImpl(ActivityDtoDao activityDtoDao) {
        this.activityDtoDao = activityDtoDao;
    }

    @Override
    public List<ActivityDto> getActivities() {
        List<ActivityDto> activities = activityDtoDao.getActivities();

        for(ActivityDto dto: activities) {
            dto.setDemands(activityDtoDao.getDemandsByActivitiesId(dto.getId()));
        }
        return activities;
    }

    @Override
    @Transactional
    public void addActivity(Activity activity) {
        activityDtoDao.addActivity(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityDtoDao.deleteActivity(id);
    }
}
