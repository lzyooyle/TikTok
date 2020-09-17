package com.dpb.mapper;

import com.dpb.pojo.Admin;

public interface AdminMapper {
    void insertAdmin(Admin admin);
    Admin queryByName(String username);
    Admin verifyAdmin(String username,String password);
}
