package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.ProjectDao;
import com.diplom.diplomspringboot.models.entity.Project;
import com.diplom.diplomspringboot.service.abstracts.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ReadWriteServiceImpl<Long, Project> implements ProjectService {

    public ProjectServiceImpl(ProjectDao dao) {
        super(dao);
    }
}
