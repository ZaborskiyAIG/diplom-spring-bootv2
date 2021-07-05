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
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String role;
    private String email;
}
