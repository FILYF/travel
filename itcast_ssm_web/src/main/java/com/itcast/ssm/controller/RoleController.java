package com.itcast.ssm.controller;

import com.itcast.ssm.service.IRoleService;
import com.itcst.ssm.domain.Permission;
import com.itcst.ssm.domain.Role;
import com.itcst.ssm.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermissions.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermissions(roleId);
        mv.addObject("permissionList",permissionList);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId, @RequestParam(name = "ids") String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
