package com.dpb.pojo;

import javax.validation.constraints.*;

/**
 * @program: springboot-ssm
 * @description: 用户的实体类
 * @author: 波波烤鸭
 * @create: 2019-05-15 19:41
 */
public class Users{
    private Integer id;
    @NotBlank(message="帐号不能为空")
    @Size(max=6,min=2,message="姓名的长度为2~6位")
    private String name;
    @Max(value=1)
    @Min(value=0)
    private Integer sex;
    @Max(value=100)
    @Min(value=0)
    private Integer age;
    @Email
    private String email;
    @NotBlank(message="地址不能为空")
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
        this.age=age;
    }
    public String getEmail(){return email;}
    public void setEmail(String email){
        this.email=email;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

}
