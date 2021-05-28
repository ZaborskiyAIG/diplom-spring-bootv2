package com.diplom.diplomspringboot.service.abstracts.dto;

import com.diplom.diplomspringboot.models.entity.Demands;

import java.util.List;

public interface DemandDtoService {

    void addDemand(Demands demands, Long id);

    void update(Demands demands, Integer predRank, Long newId);

    void update(Demands demands);

    void delete(Long id);

    List<Demands> getAll();

    List<Demands> getAllByProjectId(Long id);

    Demands getById(Long id);
}
