package com.dpb.service;

import com.dpb.pojo.Users;

import java.util.List;

public interface UsersService {

    List<Users> query();

    void insertUser(Users users);

    Users queryUsersById(Integer id);

    void updateUser(Users users);

    void delete(Integer id);
}
