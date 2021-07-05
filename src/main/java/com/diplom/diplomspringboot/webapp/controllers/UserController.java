package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.dto.UserDto;
import com.diplom.diplomspringboot.models.dto.UserInfoDto;
import com.diplom.diplomspringboot.models.entity.Role;
import com.diplom.diplomspringboot.models.entity.User;
import com.diplom.diplomspringboot.service.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO сделать через маппер
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> list = new ArrayList<>();

        List<User> users = userService.getAll();

        users.forEach(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getUserId());
            dto.setRole(user.getRole().getName());
            dto.setLogin(user.getLogin());
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            list.add(dto);
        });
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> getUser(@RequestBody UserInfoDto dto) {
        User user = userService.getUserByLogin(dto.getLogin());

        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setLogin(user.getLogin());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().getName());

        if (user.getPassword().equals(dto.getPassword())) {
            return ResponseEntity.ok().body(userDto);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registrationUser(@RequestBody UserDto dto) {

        //TODO колхоз ебаный
        User user = new User();
        user.setLastName(dto.getLastName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());

        if(dto.getRole() == null) {
            user.setRole(new Role(2L, "USER"));
        } else {

        }

        userService.persist(user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
