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
public class RatingDto {

    private Long id;
    private String nameDemand;
    private String mark;
    private List<CriteriaRankDto> criteria;
}
