package com.dpb.service.impl;

import com.dpb.pojo.User;
import com.dpb.mapper.UserRepository;
import com.dpb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: keguang
 * @ Date: 2019/7/18 10:27
 * @ version: v1.0.0
 * @ description:
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> getUserList(int pageNum,int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }
    @Override
    public User findUserById(long id){
        return userRepository.findById(id);
    }
    @Override
    public void save(User user){
        userRepository.save(user);
    }
    @Override
    public void edit(User user){
        userRepository.save(user);
    }
    @Override
    public void delete(long id){
        userRepository.deleteById(id);
    }
    @Override
    public boolean verifyLogin(User user){
        List<User> userList = userRepository.findByUserNameOrEmailOrNickName(user.getUserName(),user.getEmail(),user.getNickName());
        return userList.size()>0;
    }
    @Override
    public boolean verifyEdit(User user){
        List<User> userList = userRepository.findByUserNameAndEmailAndNickName(user.getUserName(),user.getEmail(),user.getNickName());
        return userList.size()>0;
    }    
}
