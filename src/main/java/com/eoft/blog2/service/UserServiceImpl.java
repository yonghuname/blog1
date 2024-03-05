package com.eoft.blog2.service;

import com.eoft.blog2.dao.UserRepository;
import com.eoft.blog2.po.User;
import com.eoft.blog2.util.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private UserRepository userRepository;
     @Override
     public User checkUser(String username, String password)
     {
         User user = userRepository.findByUsernameAndPassword( username, MD5util.code(password));

         user.setUpdateTime(new Date());

                 return user;
     }

    @Override
    public String registerUser(String email, String username, String password, String invitecode, String nickname) {
       User samenameuser= userRepository.findByUsername(username);
//       p判断重复用户名和 邀请码正确否
        if( samenameuser != null) {
            return "usernameExists";
        }
        if(invitecode != "zhuceyaoqingma233" ){
            return "inviteCodeInvalid";
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(MD5util.code(password)); // 假设MD5util.code是正确的密码加密方法
        user.setEmail(email);
        user.setNickname(nickname);
        user.setAvatar("https://img2.baidu.com/it/u=1024056879,1494797032&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500}");

        user.setCreateTime(new Date());

        user.setUpdateTime(new Date());
        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            // 注册成功，可以返回null或者空字符串
            return null;
        } else {
            // 注册失败，返回错误信息
            return "registrationFailed";
        }
    }
}
