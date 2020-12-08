package com.cy.asset.login.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 王成
 * @date 2020-11-30
 */
public class Login implements Serializable {

    private static final long serialVersionUID = 1797862454883405825L;

    /** 账号及工号 **/
    @NotNull(message = "员工工号不能为空")
    private String username;

    /** 登录密码 **/
    @NotNull(message = "登录密码不能为空")
    private String password;

    /** 修改的新密码 **/
    private String afterPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAfterPassword() {
        return afterPassword;
    }

    public void setAfterPassword(String afterPassword) {
        this.afterPassword = afterPassword;
    }
}
