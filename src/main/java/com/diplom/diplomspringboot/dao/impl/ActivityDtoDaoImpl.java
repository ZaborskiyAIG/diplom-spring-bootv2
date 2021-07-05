package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.ActivityDtoDao;
import com.diplom.diplomspringboot.models.dto.ActivityDto;
import com.diplom.diplomspringboot.models.dto.DemandsDto;
import com.diplom.diplomspringboot.models.entity.Activity;
import com.diplom.diplomspringboot.models.entity.ActivityFilial;
import org.springframework.stereotype.Repository;

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
                "d.rank, " +
                "d.name," +
                "d.color," +
                "d.description) from Demands d WHERE d.activity.id = :id OR d.activityFilial.id = :id order by d.rank", DemandsDto.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void addActivity(Activity activity) {
        entityManager.persist(activity);
    }

    @Override
    public void addActivityFilial(ActivityFilial activity) {
        entityManager.persist(activity);
    }

    @Override
    public void deleteActivity(Activity activity) {
        entityManager.remove(activity);
//        Query query = entityManager.createQuery("delete from Activity activity where activity.id = :id");
//        query.setParameter("id", id);
//        query.executeUpdate();
    }

    @Override
    public void deleteActivityFilial(Long id) {
        Query query = entityManager.createQuery("delete from ActivityFilial activity where activity.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Activity find(Long id) {
        return entityManager.find(Activity.class, id);
    }

    @Override
    public List<ActivityDto> getActivityFilials(Long id) {
        return entityManager.createQuery("select new com.diplom.diplomspringboot.models.dto.ActivityDto(" +
                "a.id, " +
                "a.activity.id, " +
                "a.name) from ActivityFilial a where a.activity.id = :id", ActivityDto.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void updateActivity(Activity activity) {
        entityManager.merge(activity);
    }

    @Override
    public List<Activity> getAllActivity() {
        return entityManager.createQuery("from Activity a", Activity.class).getResultList();
    }
}
