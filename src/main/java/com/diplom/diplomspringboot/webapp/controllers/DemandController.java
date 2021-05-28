package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.converters.DemandsConverter;
import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.Demands;
import com.diplom.diplomspringboot.service.abstracts.RatingService;
import com.diplom.diplomspringboot.service.abstracts.dto.DemandDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/dashboard", produces = "application/json")
public class DemandController {

    private final DemandDtoService demandDtoService;
    private final DemandsConverter demandsConverter;
    private final RatingService ratingService;

    public DemandController(DemandDtoService demandDtoService, DemandsConverter demandsConverter, RatingService ratingService) {
        this.demandDtoService = demandDtoService;
        this.demandsConverter = demandsConverter;
        this.ratingService = ratingService;
    }

    @GetMapping("/demand")
    public ResponseEntity<List<DemandsDto>> getDemands() {
        return ResponseEntity.ok().body(demandsConverter.toDto(demandDtoService.getAll()));
    }

    @PostMapping("/demand/{activityId}")
    public ResponseEntity<DemandsDto> addDemand(@PathVariable("activityId") Long id,
                                                @RequestParam Long projectId,
                                                @RequestBody DemandsDto dto) {
        Demands demands = demandsConverter.toDemand(dto);
        //TODO сделать через getByKey
        Activity activity = new Activity();
        activity.setId(id);
        demands.setActivity(activity);
        demandDtoService.addDemand(demands, projectId);
        return ResponseEntity.ok(demandsConverter.toDto(demands));
    }

    @PutMapping("/demand/{id}")
    public ResponseEntity<DemandsDto> updateIndexAndActivityDemand(@PathVariable(value = "id")Long currentId, @RequestParam(value = "predRank")Integer predRank, @RequestBody DemandsDto dto) {
        Demands demands = demandsConverter.toDemand(dto);
        demandDtoService.update(demands, predRank, currentId);
        return ResponseEntity.ok(demandsConverter.toDto(demands));
    }

    @PutMapping("/demand")
    public ResponseEntity<DemandsDto> updateDemand(@RequestBody DemandsDto dto) {
        Demands demands = demandsConverter.toDemand(dto);
        demandDtoService.update(demands);
        return ResponseEntity.ok(demandsConverter.toDto(demands));
    }

    @DeleteMapping("/demand/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long id) {
        //TODO сделать  рамках одной транзакции, нарушение ACID
        demandDtoService.delete(id);
        ratingService.deleteRatingByDemandId(id);
        return ResponseEntity.ok().body("demand deleted");
    }
}
