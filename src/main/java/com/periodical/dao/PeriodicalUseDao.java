package com.periodical.dao;

import com.periodical.bean.Record;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface PeriodicalUseDao {
    //根据用户编号查看借书的情况
    @Select("select * from circulate_records where user_id=#{userId}")
    Record findRecordsByUserId(int userId);

    //查看借相关书的数量
    @Select("elect num from circulate_records where periodical_id=#{periodicalId}")
    int selectNum(int periodicalId);

    //修改借的期刊数量
    @Update("update circulate_records set num=#{num} where periodical_id=#{periodicalId}")
    boolean update(int periodicalId,int num);

    //根据期刊编号还书
    @Delete("delete from circulate_records where periodical_id=#{periodicalId}")
    boolean returnPeriodical(int periodicalId);

    //借书
    @Insert("insert into circulate_records(borrow_date,return_date,periodical_id,user_id) value(now(),now(),#{periodicalId},#{userId},#{num})")
    @Update("update circulate_records set return_date=date_add(return_date,interval 30 day) where periodical_id=#{periodicalId} and user_id=#{userId}")
    boolean borrow(int periodicalId,int userId,int num);

    //通过用户id和期刊id续借
    @Update("update circulate_records set return_date=date_add(return_date,interval 30 day) where periodical_id=#{periodicalId} and user_id=#{userId}")
    boolean renewal(int periodicalId,int userId);
}
