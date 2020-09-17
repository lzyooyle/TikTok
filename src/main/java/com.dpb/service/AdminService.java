package com.dpb.service;

import com.dpb.pojo.Admin;
import com.dpb.pojo.Result;

public interface AdminService{
    void insertAdmin(Admin admin);
    Admin queryByName(String username);
    Admin verifyAdmin(String username,String password);
}
