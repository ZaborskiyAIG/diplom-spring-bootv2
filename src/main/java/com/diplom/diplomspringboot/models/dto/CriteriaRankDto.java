package com.diplom.diplomspringboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CriteriaRankDto {
    private Long id;
    private String nameCriteria;
    private String abbreviation;
    private Integer mark;

}
