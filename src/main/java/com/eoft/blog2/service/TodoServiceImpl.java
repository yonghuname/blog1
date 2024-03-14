package com.eoft.blog2.service;

import com.eoft.blog2.dao.TodoRepository;
import com.eoft.blog2.dao.TypeRepository;
import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.User;
import com.eoft.blog2.web.NoFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Todoitem Updatetodos(User user, Todoitem todoitem) {


        Todoitem todo  = todoRepository.getOne(todoitem.getId()) ;
        if (todo  == null) {
            throw new NoFoundException("该todo不存在");
        }

        BeanUtils.copyProperties(todoitem,todo
//                , MyBeanUtils.getNullPropertyNames(blog)
        );
        todo.setUpdateTime(new Date());

        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(User user, Long id) {
       Todoitem todoitem = todoRepository.getOne(id);
       if(todoitem.getUser().getId() == user.getId())
           todoRepository.deleteById(id);
       else throw new  NoFoundException("你的用户不匹配")  ;
    }
//还是单独写一个吧
    @Override
    public Todoitem Finishtodos(User user, Todoitem todoitem) {

        Long id =  todoitem.getId();
        Todoitem todo  =  todoRepository.getOne(id);
       if( todo.getUser().getId() == user.getId()){
//      就是 完成 取消完成 这里取反就可以了。。
        todo.setFinished(!todo.isFinished());

//        直接取反重复利用
        return todoRepository.save(todo);}
       else throw new  NoFoundException("你的用户不匹配")  ;
    }

}
