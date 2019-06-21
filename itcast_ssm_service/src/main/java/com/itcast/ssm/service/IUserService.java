package com.itcast.ssm.service;

import com.itcst.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo);
}
