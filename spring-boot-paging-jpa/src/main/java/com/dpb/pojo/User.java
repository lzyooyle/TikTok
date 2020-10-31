package com.dpb.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="t_admin")
public class User implements Serializable {
    private static final Long serialVersionUID=1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = true, unique  = true)
    private String email;
    @Column(name = "nickName", nullable = false, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String regTime;
    public User(){}
    public User(String userName,String passWord,String email,String nickName,String regTime){
        this.userName=userName;
        this.passWord=passWord;
        this.email=email;
        this.nickName=nickName;
        this.regTime=regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regTime='" + regTime + '\'' +
                '}';
    }
}
