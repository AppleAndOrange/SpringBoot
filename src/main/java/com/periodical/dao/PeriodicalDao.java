package com.periodical.dao;

import com.periodical.bean.Periodical;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface PeriodicalDao {

    /**
     *对期刊的查询面向所有用户开放
     */
    @Select("select * from periodicals where periodical_id=#{periodicalId}")
    Periodical findPeriodicalById(int periodicalId);

    @Select("select * from periodicals where periodical_name=#{periodicalName}")
    Periodical findPeriodicalByName(String periodicalName);

    //查找期刊剩余数量
    @Select("select remaining from periodicals where periodical_id=#{periodicalId}")
    int findRemainingNum(int periodicalId);

    //修改期刊剩余数量
    @Update("update periodicals set remaining=#{remaining} where periodical_id=#{periodicalId}")
    boolean updateRemaining(int periodical,int remaining);

    //修改期刊已用数量
    @Update("update periodicals set used=#{used} where periodical_id=#{periodicalId}")
    boolean updateUsed(int periodical,int used);

    //查找期刊已用的数量
    @Select("select used from periodicals where periodical_id=#{periodicalId}")
    int findUsedNum(int periodicalId);

    /**
     *对于期刊的增加和删除仅仅管理员和超级管理员可操作
     */
    @Insert("insert into periodicals(periodical_name,author,print,publish_date,remaining,used,`type`)" +
            "value(#{periodicalName},#{author},#{print},now(),#{remaining},#{used},#{type})")
    boolean addPeriodicals(String periodicalName, String author, String print, Date publishDate,int remaining,int used,String type);

    @Delete("delete * from periodicals where periodical_id=#{periodicalId}")
    boolean deletePeriodical(int periodicalId);
}
