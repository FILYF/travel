package com.itcast.ssm.dao;

import com.itcst.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.IRoleDao.findByUserId"))
    })
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users (username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);
}
