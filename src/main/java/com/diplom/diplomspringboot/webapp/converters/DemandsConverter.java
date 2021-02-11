package com.diplom.diplomspringboot.webapp.converters;

import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Demands;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DemandsConverter {

    public abstract Demands toDemand(DemandsDto dto);

    public abstract DemandsDto toDto(Demands demands);
}
