package com.cy.asset.login.service.Impl;

import com.cy.asset.common.response.SuccessResponse;
import com.cy.asset.login.bean.Login;
import com.cy.asset.login.bean.User;
import com.cy.asset.login.service.LoginService;
import com.cy.asset.login.dao.LoginDao;
import com.cy.asset.login.util.JWTUtil;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author 王成
 * @date 2020-11-24
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao loginMapperDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public SuccessResponse getLoginUser(Login loginRequest, HttpSession httpSession) {
        Login loginUser = loginMapperDao.getLoginUser(loginRequest.getUsername());
        // 账号密码是否正确
        if(null == loginUser){
            return new SuccessResponse(203,"账号不存在",null);
        }
        if(StringUtil.isEmpty(loginRequest.getPassword())
                || !encoder.matches(loginRequest.getPassword(),loginUser.getPassword())){
            return new SuccessResponse(204,"密码错误",null);
        }
        HashMap userMap = Maps.newHashMap();
        // 用户信息
        User employeeUser = loginMapperDao.getEmployeeInfo(loginRequest.getUsername());
        // 设置session
        httpSession.setAttribute("username", employeeUser.getEmpNo());
        httpSession.setAttribute("user", employeeUser);
        // 登录成功返回Token
        userMap.put("Authorization", JWTUtil.sign(loginRequest.getUsername(),loginUser.getPassword()));
        return new SuccessResponse(userMap);
    }

    @Override
    public SuccessResponse changePassword(Login loginRequest) {
        // 密码强正则校验(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-20之间)
        String regx="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        boolean passwordMatches = loginRequest.getAfterPassword().matches(regx);
        if( !passwordMatches){
            return new SuccessResponse("密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-20之间");
        }
        // 校验新旧密码是否相同
        if(loginRequest.getPassword().equals(loginRequest.getAfterPassword())){
            return new SuccessResponse("新密码与旧密码重复");
        }
        // Bcrypt 密码加密
        loginRequest.setAfterPassword(encoder.encode(loginRequest.getAfterPassword()));
        // 修改密码
        loginMapperDao.changePassword(loginRequest);
        return new SuccessResponse("密码修改成功");
    }

}
