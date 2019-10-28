package com.mhc.springboot.dao.entity;

import com.mhc.springboot.dao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 
 * </p>
 *
 * @author mhc
 * @since 2019-10-28
 */
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String description;

    private Boolean disabled;

    private String email;

    private String mobile;

    private String nickname;

    private String password;

    private String username;

    private String userType;

    @TableLogic
    private Integer deleted;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "description=" + description +
            ", disabled=" + disabled +
            ", email=" + email +
            ", mobile=" + mobile +
            ", nickname=" + nickname +
            ", password=" + password +
            ", username=" + username +
            ", userType=" + userType +
            ", deleted=" + deleted +
        "}";
    }
}
