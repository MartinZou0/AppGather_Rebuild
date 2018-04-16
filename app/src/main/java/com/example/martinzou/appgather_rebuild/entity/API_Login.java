package com.example.martinzou.appgather_rebuild.entity;

/**
 * Created by 10630 on 2018/3/27.
 */

//登录信息实体

public class API_Login {
    private String Username;//用户名
    private String Password;//密码

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public API_Login(String username, String password) {
        Username = username;
        Password = password;
    }
}
