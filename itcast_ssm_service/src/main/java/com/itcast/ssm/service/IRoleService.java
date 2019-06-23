package com.itcast.ssm.service;

import com.itcst.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;
}
