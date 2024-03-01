package com.eoft.blog2.web;

import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.Type;
import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.TypeService;
import com.eoft.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
@Controller
    public class TypeShowController {



        @Autowired
        private TypeService typeService;

        @Autowired
        private BlogService blogService;

        @GetMapping("/types")
        public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                @PathVariable Long id, Model model) {
            List<Type> types = typeService.listTypeTop(10000);
            if (id == -1) {
                id = types.get(0).getId();
            }
            BlogQuery blogQuery = new BlogQuery();
            blogQuery.setTypeId(id);


            model.addAttribute("previousPage", previousPage);
            model.addAttribute("nextPage", nextPage);
            model.addAttribute("types", types);
            model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
            model.addAttribute("activeTypeId", id);
            return "types";
        }


    }
*/

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types")
    public String types(
            @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            Model model) {
        // 如果没有提供类型ID，获取所有类型
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        // 设置活动类型ID
        model.addAttribute("activeTypeId", id);

        // 创建BlogQuery对象
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        // 创建PageRequest对象，用于分页
        PageRequest pageRequest = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());

        // 获取分页博客列表
        Page<Blog> pageBlogs = blogService.listBlog(pageRequest, blogQuery);

        // 添加到模型
        model.addAttribute("types", types);
        model.addAttribute("page", pageBlogs);
//        model.addAttribute("pageUrl", "/types?id=" + id + "&page={page}"); // 分页URL模板

        return "types";
    }
}