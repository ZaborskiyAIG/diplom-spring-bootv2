package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.dto.RatingDto;
import com.diplom.diplomspringboot.models.entity.Rating;
import com.diplom.diplomspringboot.service.abstracts.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/rating", produces = "application/json")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{patternId}")
    public ResponseEntity<List<RatingDto>> getRating(@PathVariable Long patternId,
                                                     @RequestParam Long projectId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingByPatternId(patternId, projectId));
    }

    @PatchMapping("/{ratingId}")
    public ResponseEntity<Void> saveMarkRating(@PathVariable Long ratingId, @RequestBody Integer mark) {
        Rating rating = ratingService.getByKey(ratingId);
        rating.setMark(mark);
        ratingService.persist(rating);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
