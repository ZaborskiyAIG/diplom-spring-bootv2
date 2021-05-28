package com.diplom.diplomspringboot.dao.impl;

import com.diplom.diplomspringboot.dao.abstracts.FormulaDao;
import com.diplom.diplomspringboot.models.entity.Formula;
import org.springframework.stereotype.Repository;

@Repository
public class FormulaServiceDaoImpl extends ReadWriteDaoImpl<Long, Formula> implements FormulaDao {


}
