package com.cy.asset.login.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 王成
 * @date 2020-11-30
 */
public class Login implements Serializable {

    private static final long serialVersionUID = 1797862454883405825L;

    /** 主键 **/
    private String id;

    /** 账号及工号 **/
    @NotNull(message = "员工工号不能为空")
    private String username;

    /** 登录密码 **/
    @NotNull(message = "登录密码不能为空")
    private String password;

    /** 修改的新密码 **/
    private String afterPassword;

    /** 创建人 **/
    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
