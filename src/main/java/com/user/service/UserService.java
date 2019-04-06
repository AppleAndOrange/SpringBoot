package com.user.service;

import com.user.bean.Relation;
import com.user.bean.User;

import java.util.List;

public interface UserService {
    int findPrivilege(int userId);
    User login(int userId,String password);
    boolean register(String userName,String password,int age,String sex,String phone);
    boolean updatePassword(int userId,String password,String phone);
    int findUserId();
    User findByUserId(int userId);
}
