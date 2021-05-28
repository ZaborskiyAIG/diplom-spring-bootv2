package com.diplom.diplomspringboot.service.impl.dto;

import com.diplom.diplomspringboot.dao.abstracts.ActivityDtoDao;
import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import com.diplom.diplomspringboot.service.abstracts.dto.ActivityDtoService;
import com.diplom.diplomspringboot.models.converters.ActivityConverter;
import com.diplom.diplomspringboot.models.converters.ActivityFilialConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ActivityDtoServiceImpl implements ActivityDtoService {

    private final ActivityConverter activityConverter;
    private final ActivityFilialConverter activityFilialConverter;
    private final ActivityDtoDao activityDtoDao;

    public ActivityDtoServiceImpl(ActivityDtoDao activityDtoDao, ActivityConverter activityConverter, ActivityFilialConverter activityFilialConverter) {
        this.activityDtoDao = activityDtoDao;
        this.activityFilialConverter = activityFilialConverter;
        this.activityConverter = activityConverter;
    }

    @Override
    public List<ActivityDto> getActivities() {
        List<ActivityDto> activities = activityDtoDao.getActivities();

        List<ActivityDto> newActivities = new ArrayList<>();

        for(ActivityDto dto: activities) {
            newActivities.add(dto);
            newActivities.addAll(activityDtoDao.getActivityFilials(dto.getId()));
        }

        for(ActivityDto dto: newActivities) {
            dto.setDemands(activityDtoDao.getDemandsByActivitiesId(dto.getId()));
        }
        return newActivities;
    }

    @Override
    @Transactional
    public List<ActivityDto> addActivity(Activity activity) {
        activityDtoDao.addActivity(activity);
        ActivityFilial activityFilial = ActivityFilial.builder().activity(activity).demands(new ArrayList<>()).build();
        activity.setDemands(new ArrayList<>());
        activityDtoDao.addActivityFilial(activityFilial);
        return Arrays.asList(activityConverter.toDto(activity), activityFilialConverter.toDto(activityFilial));
    }

    @Override
    @Transactional
    public void updateActivity(Activity activity) {
        activityDtoDao.updateActivity(activity);
    }

    @Override
    @Transactional
    public void deleteActivity(Long id) {
      //  activityDtoDao.deleteActivityFilial(id); //не уверен, но скорее всего это заглуша, ибо буть тут больше филиалов, оно бы наебнулось
        Activity activity = activityDtoDao.find(id);
        activityDtoDao.deleteActivity(activity);
    }
}
