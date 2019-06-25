package com.itcast.ssm.controller;


import com.itcast.ssm.service.ISysLogService;
import com.itcst.ssm.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogList);
        mv.setViewName("syslog-list");
        return mv;
    }

}
