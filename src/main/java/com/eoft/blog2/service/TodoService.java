package com.eoft.blog2.service;

import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.User;

import java.util.List;

public interface TodoService {
    List<Todoitem> getalltodos(User user);
    void Savetodos( Todoitem todoitem);
//
    Todoitem Updatetodos(User user,Todoitem todoitem);
    void deleteById(User user,Long id);
    Todoitem Finishtodos(User user,Todoitem todoitem);


}
