package com.eoft.blog2.web;

import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.TagService;
import com.eoft.blog2.service.TypeService;
import com.eoft.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.String;
@Controller


public class indexController {
@Autowired
private BlogService blogService;



    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                            Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(8));

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
    @GetMapping("/404")
    public String e404(){
        System.out.println("----------blog------------");
        return "404" ;
    }

}














