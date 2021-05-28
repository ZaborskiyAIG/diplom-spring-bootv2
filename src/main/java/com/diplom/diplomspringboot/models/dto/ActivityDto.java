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

    public ActivityDto(Long id, Long mainId, String name) {
        this.id = id;
        this.mainId = mainId;
        this.name = name;
    }

    private Long id;
    private Long mainId;
    private String name;
    private List<DemandsDto> demands;
}
