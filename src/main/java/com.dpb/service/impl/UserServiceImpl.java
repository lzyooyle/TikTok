package com.dpb.service.impl;

import com.dpb.service.UsersService;
import com.dpb.mapper.UsersMapper;
import com.dpb.pojo.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot-ssm
 * @description: 业务层的实现类
 * @author: 波波烤鸭
 * @create: 2019-05-15 19:55
 */
@Service
@Transactional
public class UserServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<Users> query() {
        return usersMapper.query();
    }

    @Override
    public void insertUser(Users users) {
        usersMapper.insertUser(users);
    }

    @Override
    public Users queryUsersById(Integer id) {
        return this.usersMapper.queryById(id);
    }

    @Override
    public void updateUser(Users users) {
        this.usersMapper.update(users);
    }

    @Override
    public void delete(Integer id) {
        this.usersMapper.delete(id);
    }
}
