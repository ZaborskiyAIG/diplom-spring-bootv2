package com.diplom.diplomspringboot.service.abstracts;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;

import java.util.List;

public interface ActivityDtoService {

    List<ActivityDto> getActivities();

    void addActivity(Activity activity);

    void deleteActivity(Long id);

}
