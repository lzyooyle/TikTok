package com.dpb.service;

import com.dpb.mapper.LoginDao;
import com.dpb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;
    public boolean verifyLogin(User user) {
        List<User> userList = loginDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userList.size() > 0;
    }
    public Iterable<User> getAll() {
        return loginDao.findAll();
    }
    public User save(User user) {
        return loginDao.save(user);
    }
    public User getById(long id) {
        Optional<User> op = loginDao.findById(id);
        return op.get();
    }
    public void update(User user) {
        loginDao.save(user);
    }
    public void delete(long id) {
        loginDao.deleteById(id);
    }
    public Iterable<User> findAllSort(Sort sort) {
        return loginDao.findAll(sort);
    }
    public Page<User> findAll(Pageable page) {
        return loginDao.findAll(page);
    }
}

