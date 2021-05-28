package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;

import java.util.List;

public interface ActivityDtoDao {

    List<ActivityDto> getActivities();

    List<DemandsDto> getDemandsByActivitiesId(Long id);

    void addActivity(Activity activity);

    void addActivityFilial(ActivityFilial activity);

    void deleteActivity(Activity activity);

    void deleteActivityFilial(Long id);

    Activity find (Long id);

    List<ActivityDto> getActivityFilials(Long id);

    void updateActivity(Activity activity);

    List<Activity> getAllActivity();
}
