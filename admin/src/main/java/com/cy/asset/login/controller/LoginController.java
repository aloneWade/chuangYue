package com.cy.asset.login.controller;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.login.bean.Login;
import com.cy.asset.login.service.LoginService;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author 王成
 * @date 2020-11-24
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public SuccessResponse doLogin(HttpServletRequest request, HttpSession httpSession){
        // 登录数据校验
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(StringUtils.isEmpty(username)){
            return new SuccessResponse(201,"登录账户不能为空！",null);
        }
        if(StringUtils.isEmpty(password)){
            return new SuccessResponse(202,"登录密码不能为空！",null);
        }
        // 登录参数
        Login loginRequest = new Login();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginService.getLoginUser(loginRequest,httpSession);
    }

    @RequestMapping(value = "/auth/info",method = RequestMethod.GET)
    public SuccessResponse getUserInfo(HttpServletRequest request){
        try{
            //获取session
            HttpSession session = request.getSession();
            HashMap userMap = Maps.newHashMap();
            userMap.put("username",session.getAttribute("username").toString());
            userMap.put("name",session.getAttribute("username").toString());
            return new SuccessResponse("user",userMap);
        }catch (NullPointerException e){
            return new SuccessResponse(401,"session is empty",e);
        }
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public SuccessResponse changePassword(@RequestBody Login loginRequest){
        if(StringUtils.isEmpty(loginRequest.getAfterPassword())){
            return new SuccessResponse("修改的新密码不能为空！");
        }
        return loginService.changePassword(loginRequest);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public SuccessResponse doLogout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        return new SuccessResponse("登出成功");
    }

    /**
     * 所有人都可以访问，但是用户与游客看到的内容不同
     */
    @GetMapping("/article")
    public SuccessResponse article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new SuccessResponse("You are already logged in");
        } else {
            return new SuccessResponse("You are guest");
        }
    }

    /**
     * 登入的用户才可以进行访问
     * 前后端交互用cookie 用户系统对接 Shiro
     */
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public SuccessResponse requireAuth() {
        return new SuccessResponse("You are authenticated");
    }

    /**
     * admin的角色用户才可以登入
     */
    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public SuccessResponse requireRole() {
        return new SuccessResponse("You are visiting require_role");
    }

    /**
     * 拥有view和edit权限的用户才可以访问
     */
    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public SuccessResponse requirePermission() {
        return new SuccessResponse("You are visiting permission require edit,view");
    }

    /**
     * token校验不通过，跳转401界面
     */
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SuccessResponse unauthorized() {
        return new SuccessResponse("Unauthorized");
    }

}