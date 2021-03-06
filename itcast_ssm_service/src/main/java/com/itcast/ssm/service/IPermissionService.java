package com.itcast.ssm.service;

import com.itcst.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String id) throws Exception;

    void deletePermission(String id) throws Exception;
}
