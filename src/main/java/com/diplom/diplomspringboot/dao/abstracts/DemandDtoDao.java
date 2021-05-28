package com.diplom.diplomspringboot.dao.abstracts;

import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import com.diplom.diplomspringboot.models.entity.Demands;

import java.util.List;

public interface DemandDtoDao {

    void addDemand(Demands demands);

    void update(Demands demands);

    List<Demands> get(Long id);

    Long getActivityIdByDemandId(Long id);

    boolean checkActivity(Long id);

    void delete(Demands demands);

    Demands find(Long id);

    List<Demands> getAll();

    List<Demands> getAllByActivityFilialAndByProjectId(Long id);

    List<Demands> getAllByActivityAndByProjectId(Long id);

    List<ActivityFilial> getActivityFilialIsNullDemand();
}
