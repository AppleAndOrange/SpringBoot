package com.user.service;

import com.user.bean.Relation;
import com.user.bean.User;
import com.user.dao.UserDao;
import oracle.net.ns.NetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
//    @Resource
    private UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao=dao;
    }


    @Override
    public int findPrivilege(int userId) {
        return dao.findPrivilege(userId);
    }

    @Override
    public User login(int userId, String password) {
        return dao.login(userId,password);
    }

    @Override
    public boolean register(String userName,String password,int age,String sex,String phone) {
        return dao.register(userName,password,age,sex,phone);
    }

    @Override
    public boolean updatePassword(int userId, String password,String phone) {
        return dao.updatePassword(userId, password,phone);
    }

    @Override
    public int findUserId() {
        return dao.findUserId();
    }

    @Override
    public User findByUserId(int userId) {
        return dao.findByUserId(userId);
    }
}
