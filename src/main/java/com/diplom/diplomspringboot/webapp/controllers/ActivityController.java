package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.converters.ActivityConverter;
import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.Project;
import com.diplom.diplomspringboot.service.abstracts.dto.ActivityDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/dashboard", produces = "application/json")
public class ActivityController {


    private final ActivityConverter activityConverter;
    private final ActivityDtoService activityDtoService;

    @Autowired
    public ActivityController(ActivityDtoService activityDtoService, ActivityConverter activityConverter) {
        this.activityDtoService = activityDtoService;
        this.activityConverter = activityConverter;
    }

    @GetMapping("/activity")
    public ResponseEntity<List<ActivityDto>> getActivities() {
        return ResponseEntity.status(HttpStatus.OK).body(activityDtoService.getActivities());
    }

    //TODO сделать создание activity к проекту, передавать к id
    @PostMapping("/activity")
    public ResponseEntity<List<ActivityDto>> addActivity(@RequestBody ActivityDto dto) {
        Activity activity = activityConverter.toActivity(dto);
        activity.setProject(new Project(1L));
        return ResponseEntity.ok(activityDtoService.addActivity(activity));
    }

    //TODO сделать нормальный id проекта
    @PutMapping("/activity")
    public ResponseEntity<ActivityDto> putActivity(@RequestBody ActivityDto dto) {
        Activity activity = activityConverter.toActivity(dto);

        activity.setProject(new Project(1L));
        activityDtoService.updateActivity(activity);
        return ResponseEntity.ok(activityConverter.toDto(activity));
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id) {
        activityDtoService.deleteActivity(id);
        return ResponseEntity.ok().body("Activity deleted");
    }
}
