package com.itcast.ssm.dao;

import com.itcst.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
   /* @Results({
        @Result(id = true,column = "id",property = "id"),
        @Result(column = "roleName",property = "roleName"),
        @Result(column = "roleDesc",property = "roleDesc"),
        @Result(column = "id",property = "permissions",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.IPermissionDao.findByRoleId")),
        @Result(column = "id",property = "users",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.IUserDao.findByRoleId")),
    })*/
    List<Role> findAll() throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permission",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.IPermissionDao.findByRoleId"))
    })
    Role findById(String id) throws Exception;


}
