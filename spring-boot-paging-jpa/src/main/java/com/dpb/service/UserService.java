package com.dpb.service;
import com.dpb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: keguang
 * @ Date: 2019/7/18 10:26
 * @ version: v1.0.0
 * @ description:
 */

/**
 * interface define methods
 */
public interface UserService{
    Page<User> getUserList(int pageNum,int pageSize);
    User findUserById(long id);
    void save(User user);
    void edit(User user);
    void delete(long id);
    boolean verifyLogin(User user);
    boolean verifyEdit(User user);
}
