package com.user.dao;

import com.user.bean.Relation;
import com.user.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {

    //获取用户权限
    @Select("select r.privilege_type from role r left join role_user_relation ru on ru.role_id=r.role_id\n" +
            " left join user u on ru.role_id=u.user_id where u.user_id=#{userId};")
    int findPrivilege(int userId);

    @Select("select user_id from user where user_id=(select max(user_id) from user)")
    int findUserId();

    //根据用户编号查询用户
    @Select("select * from user where user_id=#{userId}")
    User findByUserId(int userId);


    //登陆到相应权限的界面下
    @Select("select * from  user where user_id=#{userId} and password=#{password}")
    User login(int userId, String password);

    //注册新用户，这里仅限普通用户，不含管理员
    @Insert("insert into `user` (register_date,user_name,`password`,age,sex,phone) value(now(),#{userName},#{password},#{age},#{sex},#{phone})")
    boolean register(String userName,String password,int age,String sex,String phone);

    //修改密码
    @Update("update `user` set `password` = #{password} and phone=#{phone} where userId=#{userId}")
    boolean updatePassword(int userId,String password,String phone);

}
