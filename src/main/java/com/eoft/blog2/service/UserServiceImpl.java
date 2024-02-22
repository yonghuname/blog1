package com.eoft.blog2.service;

import com.eoft.blog2.dao.UserRepository;
import com.eoft.blog2.po.User;
import com.eoft.blog2.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private UserRepository userRepository;
     @Override
     public User checkUser(String username, String password)
     {
         User user = userRepository.findByUsernameAndPassword( username, MD5util.code(password));
                 return user;
     }
}
