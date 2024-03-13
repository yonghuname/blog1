package com.eoft.blog2.service;

import com.eoft.blog2.dao.TodoRepository;
import com.eoft.blog2.dao.TypeRepository;
import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.User;
import com.eoft.blog2.web.NoFoundException;
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

    @Override
    public void deleteById(User user, Long id) {
       Todoitem todoitem = todoRepository.getOne(id);
       if(todoitem.getUser().getId() == user.getId())
           todoRepository.deleteById(id);
       else throw new  NoFoundException("你的用户不匹配")  ;
    }
}
