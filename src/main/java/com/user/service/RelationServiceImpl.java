package com.user.service;

import com.user.bean.Relation;
import com.user.dao.RelationDao;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl implements RelationService {
    private RelationDao dao;

    public RelationServiceImpl(RelationDao dao) {
        this.dao = dao;
    }


    @Override
    public boolean update(int userId, int roleId) {
        return dao.update(userId,roleId);
    }

    @Override
    public boolean insert(int userId, int roleId) {
        return dao.insert(userId,roleId);
    }


}
