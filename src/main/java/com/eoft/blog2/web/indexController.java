package com.eoft.blog2.web;

import org.springframework.context.annotation.Import;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.String;
@Controller


public class indexController {

    @GetMapping("/")
    public String index(){
//        int i = 9 / 0;
/*
        String blog = null;
        if(blog == null){
           throw new NoFoundException("文章不存在");
        }
*/
        System.out.println("----------index------------");
        return "index" ;


    }
    @GetMapping("/blog")
    public String blog(){
        System.out.println("----------blog------------");
        return "blog" ;
    }

}














