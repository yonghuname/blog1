package com.eoft.blog2.dao;

import com.eoft.blog2.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username );

}