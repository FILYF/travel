package com.itcast.ssm.service.Impl;

import com.itcast.ssm.dao.ISysLogDao;
import com.itcast.ssm.service.ISysLogService;
import com.itcst.ssm.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
