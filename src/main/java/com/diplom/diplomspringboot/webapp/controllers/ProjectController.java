package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.dto.ProjectDto;
import com.diplom.diplomspringboot.service.abstracts.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/project", produces = "application/json")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //TODO маппер
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getPrjects() {
        List<ProjectDto> list = new ArrayList<>();
        projectService.getAll().forEach(result -> {
            list.add(new ProjectDto(result.getId(), result.getName()));
        });
        return ResponseEntity.ok().body(list);
    }

}
