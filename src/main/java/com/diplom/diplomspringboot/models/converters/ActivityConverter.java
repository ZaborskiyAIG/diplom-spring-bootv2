package com.diplom.diplomspringboot.models.converters;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ActivityConverter {

    public abstract Activity toActivity(ActivityDto dto);

    @Mapping(target = "mainId", ignore = true)
    public abstract ActivityDto toDto(Activity activity);
}
