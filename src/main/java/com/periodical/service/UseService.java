package com.periodical.service;

import com.periodical.bean.Record;

import java.sql.Date;

public interface UseService {
    Record findRecordsByUserId(int userId);
    boolean update(int periodicalId,int num);
    boolean returnPeriodical(int periodicalId);
    boolean borrow(int periodicalId, int userId, int num);
    boolean renewal(int periodicalId,int userId);
    int selectNum(int periodicalId);
}
