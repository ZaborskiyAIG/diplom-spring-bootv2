package com.diplom.diplomspringboot.models.converters;

import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Demands;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DemandsConverter {

    public abstract Demands toDemand(DemandsDto dto);

    public abstract DemandsDto toDto(Demands demands);

    public abstract List<DemandsDto> toDto(List<Demands> eList);

}
