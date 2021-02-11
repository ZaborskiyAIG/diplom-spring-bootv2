package com.diplom.diplomspringboot.models.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityDto {

    public ActivityDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;
    private List<DemandsDto> demands;

}
