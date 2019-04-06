package com.user.service;

import com.user.bean.Relation;

public interface RelationService {
    boolean update(int userId,int roleId);
    boolean insert(int userId,int roleId);
}
