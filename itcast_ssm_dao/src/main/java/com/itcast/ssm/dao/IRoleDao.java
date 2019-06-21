package com.itcast.ssm.dao;

import com.itcst.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    List<Role> findByUserId(String userId) throws Exception;
}
