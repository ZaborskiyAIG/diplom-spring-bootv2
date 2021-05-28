package com.diplom.diplomspringboot.webapp.controllers;

import com.diplom.diplomspringboot.models.dto.FormulaDto;
import com.diplom.diplomspringboot.models.entity.Formula;
import com.diplom.diplomspringboot.service.abstracts.FormulaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/formula", produces = "application/json")
public class FormulaController {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<FormulaDto> getResultFormula(@PathVariable Long id) {
        Formula formula = formulaService.getByKey(id);
        return ResponseEntity.ok().body(new FormulaDto(formula.getId(), formula.getFormula()));
    }
}
