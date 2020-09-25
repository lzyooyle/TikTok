package com.dpb.mapper;

import com.dpb.pojo.Admin;
import com.dpb.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mapper的具体表达式
 */
@Mapper
@Repository
public interface UserMapper{
    @Select(value="select username,password from t_admin where username=#{username}")
    @Results({
            @Result(property="username",column="username"),
            @Result(property="password",column="password")
    })
    User findUserByName(@Param("username") String username);
    @Insert("insert into t_admin values(#{id},#{username},#{password})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    void regist(User user);
    @Select("select id from t_admin where username=#{username} and password=#{password}")
    Long login(User user);
    @Insert("insert into t_user(name,sex,age,email,address) values(#{name},#{sex},#{age},#{email},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer save(Admin admin);
    @Select("select * from t_user")
    List<Admin> query();
    @Delete("delete from t_user where id=#{id}")
    void delete(Integer id);
    @Select("select * from t_user where id=#{id}")
    Admin updateInfo(Integer id);
    @Update("update t_user set name=#{name},age=#{age},sex=#{sex},email=#{email},address=#{address} where id=#{id}")
    void updateUser(Admin admin);
}
