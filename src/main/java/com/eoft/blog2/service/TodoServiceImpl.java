package com.eoft.blog2.service;

import com.eoft.blog2.dao.TodoRepository;
import com.eoft.blog2.dao.TypeRepository;
import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements  TodoService{
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public List<Todoitem> getalltodos(User user){
     Long uid =   user.getId();
       List<Todoitem>  todolist= todoRepository.findAllByUId(uid);
    return todolist;
    }
    public void Savetodos(Todoitem todoitem){
        todoRepository.save(todoitem);
    }
}
