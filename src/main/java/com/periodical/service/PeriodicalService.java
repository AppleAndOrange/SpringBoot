package com.periodical.service;

import com.periodical.bean.Periodical;

import java.sql.Date;

public interface PeriodicalService {
    Periodical findPeriodicalById(int periodicalId);
    Periodical findPeriodicalByName(String periodicalName);
    boolean addPeriodicals(String periodicalName, String author, String print, Date publishDate, int remaining, int used, String type);
    boolean deletePeriodical(int periodicalId);
    int findRemainingNum(int periodicalId);
    boolean updateRemaining(int periodical,int remaining);
    int findUsedNum(int periodicalId);
    boolean updateUsed(int periodical,int used);
}
