package com.diplom.diplomspringboot.service.abstracts.dto;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;

import java.util.List;

public interface ActivityDtoService {

    List<ActivityDto> getActivities();

    List<ActivityDto> addActivity(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivity(Long id);

}
