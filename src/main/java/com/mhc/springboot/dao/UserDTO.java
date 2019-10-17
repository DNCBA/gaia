package com.mhc.springboot.dao;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 19:03
 */
public class UserDTO {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
