package com.diplom.diplomspringboot.service.impl;

import com.diplom.diplomspringboot.dao.abstracts.ReadWriteDao;
import com.diplom.diplomspringboot.dao.abstracts.ScaleDao;
import com.diplom.diplomspringboot.models.entity.Scale;
import com.diplom.diplomspringboot.service.abstracts.ScaleService;
import org.springframework.stereotype.Service;

@Service
public class ScaleServiceImpl extends ReadWriteServiceImpl<Long, Scale> implements ScaleService {

    public ScaleServiceImpl(ScaleDao dao) {
        super(dao);
    }

}
