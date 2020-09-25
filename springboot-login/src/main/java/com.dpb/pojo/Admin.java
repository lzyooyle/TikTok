package com.dpb.pojo;

import javax.validation.constraints.*;

public class Admin {
    private Integer id;
    @NotBlank(message="姓名不能为空")
    private String name;
    @Min(value=0)
    @Max(value=1)
    @NotNull(message="请选择您的性别")
    private Integer sex;
    @Min(value=0)
    @Max(value=99)
    @NotNull(message="年龄超出了限制的范围")
    private Integer age;
    @Email
    @NotBlank(message="请输入正确的邮箱")
    private String email;
    private String address;
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Integer getSex(){
        return sex;
    }
    public void setSex(Integer sex){
        this.sex=sex;
    }
    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String toString(){
        return id+name+sex+age+email+address;
    }
}
