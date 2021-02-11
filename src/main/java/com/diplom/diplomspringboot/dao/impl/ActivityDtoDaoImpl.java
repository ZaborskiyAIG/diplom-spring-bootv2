package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.ActivityDtoDao;
import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ActivityDtoDaoImpl implements ActivityDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ActivityDto> getActivities() {
        return entityManager.createQuery("select new com.diplom.diplomspringboot.models.dto.ActivityDto(" +
                "a.id, " +
                "a.name) from Activity a ", ActivityDto.class).getResultList();
    }

    @Override
    public List<DemandsDto> getDemandsByActivitiesId(Long id) {
        return entityManager.createQuery("select new com.diplom.diplomspringboot.models.dto.DemandsDto(" +
                "d.id, " +
                "d.name," +
                "d.color," +
                "d.description) from Demands d WHERE d.activity.id = :id", DemandsDto.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void addActivity(Activity activity) {
        entityManager.persist(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        Query query = entityManager.createQuery("delete from Activity activity where activity.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
