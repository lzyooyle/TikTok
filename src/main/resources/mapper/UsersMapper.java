<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.dpb.mapper.UsersMapper">
    <select id="query" resultType="Users">
        select * from t_user
    </select>

    <insert id="insertUser" parameterType="Users">
        insert into t_user(name,sex,age,email,address)values(#{name},#{sex},#{age},#{email},#{address})
    </insert>

    <select id="queryById"  resultType="Users">
        select * from t_user where id = #{id}
    </select>

    <update id="update" parameterType="Users">
        update t_user set name=#{name},sex=#{sex},age=#{age},email=#{email},address=#{address} where id = #{id}
    </update>

    <delete id="delete">
        delete from t_user where id=#{id}
    </delete>
</mapper>
