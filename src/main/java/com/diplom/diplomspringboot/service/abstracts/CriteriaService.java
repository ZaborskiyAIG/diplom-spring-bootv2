package com.diplom.diplomspringboot.service.abstracts;

import com.diplom.diplomspringboot.models.entity.Criteria;

import java.util.List;

public interface CriteriaService extends ReadWriteService<Long, Criteria> {

    List<Criteria> getAllByPatternId(Long id);

}
