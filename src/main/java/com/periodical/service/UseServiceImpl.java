package com.periodical.service;

import com.periodical.bean.Record;
import com.periodical.dao.PeriodicalUseDao;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UseServiceImpl implements UseService {
    private PeriodicalUseDao dao;

    public UseServiceImpl(PeriodicalUseDao dao) {
        this.dao = dao;
    }

    @Override
    public Record findRecordsByUserId(int userId) {
        return dao.findRecordsByUserId(userId);
    }

    @Override
    public boolean update(int periodicalId,int num) {
        return dao.update(periodicalId,num);
    }

    @Override
    public boolean returnPeriodical(int periodicalId) {
        return dao.returnPeriodical(periodicalId);
    }

    @Override
    public boolean borrow( int periodicalId, int userId, int num) {
        return dao.borrow( periodicalId, userId, num);
    }

    @Override
    public boolean renewal(int periodicalId,int userId) {
        return dao.renewal(periodicalId,userId);
    }

    @Override
    public int selectNum(int periodicalId) {
        return dao.selectNum(periodicalId);
    }
}
