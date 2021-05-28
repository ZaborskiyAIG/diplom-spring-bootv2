package com.diplom.diplomspringboot.models.converters;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ActivityFilialConverter {


    public abstract ActivityFilial toActivity(ActivityDto dto);

    @Mapping(source = "activity.id", target = "mainId")
    public abstract ActivityDto toDto(ActivityFilial activity);
}
