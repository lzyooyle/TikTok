package com.dpb.mapper;

import com.dpb.pojo.Users;

import java.util.List;

/**
 * @program: springboot-ssm
 * @description: 用户实体对应的mapper接口
 * @author: 波波烤鸭
 * @create: 2019-05-15 19:46
 */
public interface UsersMapper{
    List<Users> query();
    void insertUser(Users users);
    Users queryById(Integer id);
    void update(Users users);
    void delete(Integer id);
}
