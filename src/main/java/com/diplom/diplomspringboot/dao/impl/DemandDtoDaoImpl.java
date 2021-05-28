package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.DemandDtoDao;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import com.diplom.diplomspringboot.models.entity.Demands;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DemandDtoDaoImpl implements DemandDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addDemand(Demands demands) {
        entityManager.persist(demands);
    }

    @Override
    public void update(Demands demands) {
        entityManager.merge(demands);
    }

    @Override
    public List<Demands> get(Long id) {
        return entityManager.createQuery("select d from Demands d WHERE d.activity.id = :id OR d.activityFilial.id = :id order by d.rank", Demands.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Long getActivityIdByDemandId(Long id) {
        List<Activity> activities = entityManager.createQuery("select d.activity from Demands d join d.activity WHERE d.id = :id", Activity.class)
                .setParameter("id", id)
                .getResultList();

        if (activities.isEmpty()) {
            return entityManager.createQuery("select d.activityFilial from Demands d join d.activityFilial WHERE d.id = :id", ActivityFilial.class)
                    .setParameter("id", id)
                    .getSingleResult().getId();
        } else {
            return activities.get(0).getId();
        }

    }

    @Override
    public boolean checkActivity(Long id) {
        List<Activity> activities = entityManager.createQuery(" from Activity activity WHERE activity.id = :id", Activity.class)
                .setParameter("id", id)
                .getResultList();
        return activities.isEmpty();
    }

    @Override
    public void delete(Demands demands) {
        entityManager.remove(demands);
    }

    @Override
    public Demands find(Long id) {
        return entityManager.find(Demands.class, id);
    }

    @Override
    public List<Demands> getAll() {
        return entityManager.createQuery("from Demands demand", Demands.class).getResultList();
    }

    //TODO сделать одним запросом
    @Override
    public List<Demands> getAllByActivityAndByProjectId(Long id) {
        return entityManager.createQuery("from Demands demand WHERE demand.activity.project.id = :id" , Demands.class) // demand.activity.project.id = :id + "OR demand.activityFilial.activity.project.id = :id"
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Demands> getAllByActivityFilialAndByProjectId(Long id) {
        return entityManager.createQuery("from Demands demand WHERE demand.activityFilial.activity.project.id = :id " , Demands.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<ActivityFilial> getActivityFilialIsNullDemand() {
        return entityManager.createQuery("SELECT af from ActivityFilial af left join af.demands as d ON d.activityFilial.id = af.id WHERE d.id is null" , ActivityFilial.class)
                .getResultList();
    }
}
