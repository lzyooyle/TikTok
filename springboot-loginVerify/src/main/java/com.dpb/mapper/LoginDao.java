package com.dpb.mapper;

import com.dpb.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoginDao extends PagingAndSortingRepository<User, Long> {
    public List<User> findByUsernameAndPassword(String name, String password);
}
