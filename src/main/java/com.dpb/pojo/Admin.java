package com.dpb.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Admin {
    private Integer id;
    @NotBlank(message="用户名不能为空")
    private String username;
    @Size(max=16,min=6,message="密码长度为6-16位")
    private String password;
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getUsername(){
        //System.out.println(123);
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
