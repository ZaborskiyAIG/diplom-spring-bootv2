package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.service.abstracts.ActivityDtoService;
import com.diplom.diplomspringboot.webapp.converters.ActivityConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/activity")
    public ResponseEntity<ActivityDto> addActivity(@RequestBody ActivityDto dto) {
        Activity activity = activityConverter.toActivity(dto);
        activityDtoService.addActivity(activity);
        return ResponseEntity.ok(activityConverter.toDto(activity));
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id) {
        activityDtoService.deleteActivity(id);
        return ResponseEntity.ok().body("User deleted");
    }
}
