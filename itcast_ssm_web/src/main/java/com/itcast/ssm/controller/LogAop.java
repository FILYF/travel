package com.itcast.ssm.controller;

import com.itcast.ssm.service.ISysLogService;
import com.itcst.ssm.domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Controller
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法
    // 主要获取访问时间、访问的类、访问的方法

    @Before("execution(* com.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();  // 访问时间
        // 获取访问的类
        executionClass = jp.getTarget().getClass();
        // 获取访问的方法
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        // 获取方法参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0){
            executionMethod = executionClass.getMethod(methodName); //获取无参方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++){
                classArgs[i] = args[i].getClass();

            }
            executionClass.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* com.itcast.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        Long time = new Date().getTime() - startTime.getTime(); // 获取访问时长

        String url = "";
        if(executionClass != null && executionMethod != null && executionClass != LogAop.class){
            // 1.获取类上的@RequestMapping("/order")
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                String[] classValue = classAnnotation.value();
                // 2.获取方法上的@RequestMapping
                RequestMapping methodAnnotation = (RequestMapping) executionMethod.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];

                    // 获取访问ip
                    String ip = request.getRemoteAddr();

                    // 获取当前操作用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    // 封装日志信息
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setMethod("类名" + executionClass.getName() + "方法名" + executionMethod.getName());
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(startTime);

                    sysLogService.save(sysLog);
                }
            }

        }
    }

}
