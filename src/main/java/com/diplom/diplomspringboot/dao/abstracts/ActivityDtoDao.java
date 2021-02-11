package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Activity;

import java.util.List;

public interface ActivityDtoDao {

    List<ActivityDto> getActivities();

    List<DemandsDto> getDemandsByActivitiesId(Long id);

    void addActivity(Activity activity);

    void deleteActivity(Long id);
}
