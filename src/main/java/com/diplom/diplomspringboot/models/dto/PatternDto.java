package com.diplom.diplomspringboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatternDto {

    private Long id;
    private String name;
    private String formulaId;
    private String formula;
    private String scaleId;
    private Integer min;
    private Integer max;
    private Integer step;
    private List<CriteriaDto> criteria;
}
