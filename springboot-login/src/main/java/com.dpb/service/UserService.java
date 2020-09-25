package com.dpb.service;

import com.dpb.pojo.Admin;
import com.dpb.pojo.Result;
import com.dpb.pojo.User;
import com.dpb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=RuntimeException.class)
public class UserService{
    @Autowired
    private UserMapper userMapper;
    public Result regist(User user){
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            User existUser = userMapper.findUserByName(user.getUsername());
            if(existUser!=null){
                result.setMsg("用户名已存在");
            }else{
                userMapper.regist(user);
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        }catch(Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    public Result login(User user){
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Long userId = userMapper.login(user);
            if(userId==null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                result.setDetail(user);
            }
        }catch(Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    public Result save(Admin admin){
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Integer adminId = userMapper.save(admin);
            if(adminId==null){
                result.setMsg("数据录入失败");
            }else{
                result.setMsg("数据录入成功");
                result.setSuccess(true);
                admin.setId(adminId);
                result.setDetail(admin);
            }
        }catch(Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    public List<Admin> query(){
        return userMapper.query();
    }
    public void delete(Integer id){
        this.userMapper.delete(id);
    }
    public Admin updateInfo(Integer id){
        return userMapper.updateInfo(id);
    }
    public void updateUser(Admin admin){
        this.userMapper.updateUser(admin);
    }
}
