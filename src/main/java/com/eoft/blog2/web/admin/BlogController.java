package com.eoft.blog2.web.admin;


import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.User;
import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.TagService;
import com.eoft.blog2.service.TypeService;
import com.eoft.blog2.vo.BlogQuery;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class BlogController {


    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                        Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
//      todo  原来如此 函数上面的参数是从前端得到的，而且这些 都是往下面service传递的。这就是要对上接口的原因
        return "admin/blogs";
    }






    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        Blog blog = new Blog();

        model.addAttribute("blog", new Blog());

                return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }


    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model,HttpSession session) {
        Blog blog= blogService.getBlog(id);

        User currentUser = (User) session.getAttribute("user");
//获得当前用户，检测当前用户和文章用户一样不一样。一样或者是管理员。不一样就报错到404，
        if( currentUser.getId().equals(    blog.getUser().getId() ) || currentUser.getId() == 2)  {


            setTypeAndTag(model);
            //编辑器
            System.out.println("编辑文章功能启动");
            blog.init();

            model.addAttribute("blog", blog);
            return "admin/blogs-input";
            //        return INPUT;
        }
        else {

            return "/error/noright";
        }
    }



    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {

        System.out.println(blog.getFirstPicture());
        if(blog.getFirstPicture().equals("1")){

            System.out.println("进入if；了  ---------");
            blog.setFirstPicture("https://img1.baidu.com/it/u=314735915,3692012565&fm=253&fmt=auto&app=138&f=JPEG?w=417&h=260");
        }
        blog.setUser((User) session.getAttribute("user")); // 强制类型转换 ？
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return REDIRECT_LIST;
    }


    @GetMapping("/blogs/{id}/delete")
//    tmd
    public String delete(@PathVariable Long id, RedirectAttributes attributes,HttpSession session) {
      Blog blog= blogService.getBlog(id);

        User currentUser = (User) session.getAttribute("user");
//获得当前用户，检测当前用户和文章用户一样不一样。一样或者是管理员。不一样就报错到404，
       if( currentUser.getId().equals(    blog.getUser().getId() ) || currentUser.getId()==2){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
       }
       else {  attributes.addFlashAttribute("message", "删除失败");


       } return "/error/noright";
    }


}











