package com.diplom.diplomspringboot.models.converters;

import com.diplom.diplomspringboot.models.dto.CriteriaDto;
import com.diplom.diplomspringboot.models.dto.PatternDto;
import com.diplom.diplomspringboot.models.entity.Criteria;
import com.diplom.diplomspringboot.models.entity.Pattern;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CriteriaConverter {

    Criteria toEntity(CriteriaDto dto);

    List<Criteria> toEntity(List<CriteriaDto> dList);

    CriteriaDto toDto(Criteria activity);

    List<CriteriaDto> toDto(List<Criteria> eList);

}
