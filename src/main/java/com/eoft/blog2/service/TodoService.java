package com.eoft.blog2.service;

import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.User;

import java.util.List;

public interface TodoService {
    List<Todoitem> getalltodos(User user);
    void Savetodos( Todoitem todoitem);
//
//    Todoitem Updatetodos(User user,Todoitem id);
//    Todoitem deletetodos(User user,Todoitem id);


}
