package com.dpb.mapper;

import com.dpb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: keguang
 * @ Date: 2019/7/18 10:23
 * @ version: v1.0.0
 * @ description:
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findById(long id);
    void deleteById(long id);
    List<User> findByUserNameOrEmailOrNickName(String username,String email,String nickname);
}
