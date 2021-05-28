package com.diplom.diplomspringboot.models.converters;

import com.diplom.diplomspringboot.models.dto.PatternDto;
import com.diplom.diplomspringboot.models.entity.Pattern;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatternConverter {

    @Mapping(target = "formula.formula", source = "formula")
    @Mapping(target = "scale.firstElement", source = "min")
    @Mapping(target = "scale.lastElement", source = "max")
    @Mapping(target = "scale.step", source = "step")
    @Mapping(target = "formula.id", source = "formulaId")
    @Mapping(target = "scale.id", source = "scaleId")
    Pattern toEntity(PatternDto dto);

    List<Pattern> toEntity(List<PatternDto> dList);

    @Mapping(source = "formula.formula", target = "formula")
    @Mapping(source = "scale.firstElement", target = "min")
    @Mapping(source = "scale.lastElement", target = "max")
    @Mapping(source = "scale.step", target = "step")
    @Mapping(source = "formula.id", target = "formulaId")
    @Mapping(source = "scale.id", target = "scaleId")
    PatternDto toDto(Pattern entity);

    List<PatternDto> toDto(List<Pattern> eList);
}
