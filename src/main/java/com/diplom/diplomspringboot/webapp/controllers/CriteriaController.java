package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.converters.CriteriaConverter;
import com.diplom.diplomspringboot.models.converters.PatternConverter;
import com.diplom.diplomspringboot.models.dto.CriteriaDto;
import com.diplom.diplomspringboot.models.dto.PatternDto;
import com.diplom.diplomspringboot.models.entity.Criteria;
import com.diplom.diplomspringboot.models.entity.Demands;
import com.diplom.diplomspringboot.models.entity.Pattern;
import com.diplom.diplomspringboot.models.entity.Rating;
import com.diplom.diplomspringboot.service.abstracts.CriteriaService;
import com.diplom.diplomspringboot.service.abstracts.FormulaService;
import com.diplom.diplomspringboot.service.abstracts.PatternService;
import com.diplom.diplomspringboot.service.abstracts.ProjectService;
import com.diplom.diplomspringboot.service.abstracts.RatingService;
import com.diplom.diplomspringboot.service.abstracts.ScaleService;
import com.diplom.diplomspringboot.service.abstracts.dto.DemandDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/pattern", produces = "application/json")
public class CriteriaController {


    private final CriteriaService criteriaService;
    private final CriteriaConverter criteriaConverters;
    private final PatternService patternService;
    private final PatternConverter patternConverter;
    private final FormulaService formulaService;
    private final ScaleService scaleService;
    private final DemandDtoService demandDtoService;
    private final ProjectService projectService;
    private final RatingService ratingService;

    public CriteriaController(CriteriaService criteriaService,
                              CriteriaConverter criteriaConverters,
                              PatternService patternService,
                              PatternConverter patternConverter,
                              FormulaService formulaService,
                              ScaleService scaleService,
                              DemandDtoService demandDtoService,
                              RatingService ratingService,
                              ProjectService projectService) {
        this.criteriaService = criteriaService;
        this.criteriaConverters = criteriaConverters;
        this.patternService = patternService;
        this.patternConverter = patternConverter;
        this.formulaService = formulaService;
        this.scaleService = scaleService;
        this.demandDtoService = demandDtoService;
        this.ratingService = ratingService;
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<PatternDto>> getPattern() {
        return ResponseEntity.ok().body(patternConverter.toDto(patternService.getAll()));
    }

    @PostMapping
    public ResponseEntity<PatternDto> postPattern(@RequestBody PatternDto dto) {

        Pattern pattern = patternConverter.toEntity(dto);

        if (pattern.getId() == null) {
            formulaService.persist(pattern.getFormula());
            scaleService.persist(pattern.getScale());
            patternService.persist(pattern);

            //TODO перенести в сервис
            projectService.getAll().forEach(value -> {
                value.getPatterns().add(pattern);
                projectService.persist(value);
            });

        } else {
            formulaService.update(pattern.getFormula());
            scaleService.update(pattern.getScale());
            patternService.update(pattern);
        }

        return ResponseEntity.ok().body(patternConverter.toDto(pattern));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PatternDto>> deletePattern(@PathVariable("id") Long id) {
        Pattern pattern = patternService.getByKey(id);
        pattern.getCriteria().forEach(x -> criteriaService.deleteWithCascadeEnableId(x.getId()));
        patternService.deleteWithCascadeEnableId(pattern.getId());
        return ResponseEntity.ok().body(patternConverter.toDto(patternService.getAll()));
    }

    @DeleteMapping("/criteria/{id}")
    public ResponseEntity<List<CriteriaDto>> deleteCriteria(@PathVariable("id") Long id) {
        //TODO сделать  рамках одной транзакции, нарушение ACID
        Criteria criteria = criteriaService.getByKey(id);
        ratingService.deleteRatingByCriteriaId(id);
        criteriaService.deleteWithCascadeIgnore(id);
        return ResponseEntity.ok().body(criteriaConverters.toDto(criteriaService.getAllByPatternId(criteria.getPattern().getId())));
    }

    @PostMapping("/{id}/criteria")
    public ResponseEntity<CriteriaDto> deleteCriteria(@PathVariable("id") Long id,
                                                      @RequestBody CriteriaDto dto,
                                                      @RequestParam Long projectId) {
        Pattern pattern = patternService.getByKey(id);
        Criteria criteria = criteriaConverters.toEntity(dto);
        criteria.setPattern(pattern);
        criteriaService.persist(criteria);
        List<Demands> demands = demandDtoService.getAllByProjectId(projectId);

        demands.forEach(value -> {
            Rating rating = new Rating();
            rating.setDemands(value);
            rating.setCriteria(criteria);
            ratingService.persist(rating);
        });

        return ResponseEntity.ok().body(criteriaConverters.toDto(criteria));
    }
}