package com.diplom.diplomspringboot.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DemandsDto {

    private Long id;
    private String name;
    private String color;
    private String description;
}
