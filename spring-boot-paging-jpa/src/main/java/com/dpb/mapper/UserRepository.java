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
    @Query("select a from User a where a.id <> ?1 and (a.userName = ?2 or a.email =?3 or a.nickName = ?4)")
    List<User> findIdByUserNameByEmailByNickName(Long id, String username, String email, String nickname);
}
