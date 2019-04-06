package com.periodical.service;

import com.periodical.bean.Periodical;
import com.periodical.dao.PeriodicalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PeriodicalServiceImpl implements PeriodicalService {

    private PeriodicalDao dao;

    @Autowired
    public PeriodicalServiceImpl(PeriodicalDao dao) {
        this.dao = dao;
    }

    @Override
    public Periodical findPeriodicalById(int periodicalId) {
        return dao.findPeriodicalById(periodicalId);
    }

    @Override
    public Periodical findPeriodicalByName(String periodicalName) {
        return dao.findPeriodicalByName(periodicalName);
    }

    @Override
    public boolean addPeriodicals(String periodicalName, String author, String print, Date publishDate, int remaining, int used, String type) {
        return dao.addPeriodicals(periodicalName, author, print, publishDate, remaining, used, type);
    }

    @Override
    public boolean deletePeriodical(int periodicalId) {
        return dao.deletePeriodical(periodicalId);
    }

    @Override
    public int findRemainingNum(int periodicalId) {
        return dao.findRemainingNum(periodicalId);
    }

    @Override
    public boolean updateRemaining(int periodical, int remaining) {
        return dao.updateRemaining(periodical,remaining);
    }

    @Override
    public int findUsedNum(int periodicalId) {
        return dao.findUsedNum(periodicalId);
    }

    @Override
    public boolean updateUsed(int periodical, int used) {
        return dao.updateUsed(periodical,used);
    }
}
