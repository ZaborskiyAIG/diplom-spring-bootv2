package com.diplom.diplomspringboot.webapp.converters;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ActivityConverter {

    public abstract Activity toActivity(ActivityDto dto);

    public abstract ActivityDto toDto(Activity activity);
}
