package com.eoft.blog2.web;

import com.eoft.blog2.po.Blog;
import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.TagService;
import com.eoft.blog2.service.TypeService;
import com.eoft.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
//        System.out.println();
        return "blog";
    }

    /*
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";}
*/
    @GetMapping("/search")
    public String searchBlogs(
            @PageableDefault(size =2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String query,
            Model model) {
        // 使用博客服务根据查询和分页信息获取博客列表
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));

        // 将分页结果和查询添加到模型中
        model.addAttribute("query", query);

        // 返回视图名称，Thymeleaf 将渲染指定的片段
//        return "admin/blogs :: blogList";
        return "search";}



    @GetMapping("/404")
    public String e404(){
        System.out.println("----------blog------------");
        return "404" ;
    }


}














