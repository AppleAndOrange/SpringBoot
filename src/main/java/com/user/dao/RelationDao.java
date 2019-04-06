package com.user.dao;

import com.user.bean.Relation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RelationDao {

    //修改用户权限
    @Update("update role_user_relation set role_id=#{roleId} where user_id=#{userId}")
    boolean update(int userId,int roleId);

    //将用户与权限进行映射
    @Insert("insert into role_user_relation(user_id,role_id) value(#{userId},#{roleId})")
    boolean insert(int userId,int roleId);

}
