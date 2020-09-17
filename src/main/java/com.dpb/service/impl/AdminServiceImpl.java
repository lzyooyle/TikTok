package com.dpb.service.impl;

import com.dpb.mapper.AdminMapper;
import com.dpb.pojo.Admin;
import com.dpb.pojo.Result;
import com.dpb.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public void insertAdmin(Admin admin){
        adminMapper.insertAdmin(admin);
    }
    @Override
    public Admin queryByName(String username){
        return this.adminMapper.queryByName(username);
    }
    @Override
    public Admin verifyAdmin(String username,String password){
        return this.adminMapper.verifyAdmin(username,password);
    }
}
