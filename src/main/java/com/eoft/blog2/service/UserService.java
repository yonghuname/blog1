package com.eoft.blog2.service;

import com.eoft.blog2.po.User;

public interface UserService {
    User checkUser (String username,String password);


    User updateUser(String password, String nickname, Long id,String avatar);

    String registerUser(String email,String username,String password,String invitecode,String nickname);
}
