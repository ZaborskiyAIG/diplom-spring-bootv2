package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.ProjectDao;
import com.diplom.diplomspringboot.models.entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl extends ReadWriteDaoImpl<Long, Project> implements ProjectDao {
}
