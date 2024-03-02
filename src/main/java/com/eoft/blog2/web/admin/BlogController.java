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

import java.util.List;


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

//    todo 要按userid 搜索
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                        Pageable pageable, BlogQuery blog, Model model ,HttpSession session) {

        User currentUser = (User) session.getAttribute("user");
//       Long uid = currentUser.getId();
//是这个blog
        model.addAttribute("types", typeService.listType());

        model.addAttribute("page",blogService.blogssearch(pageable,blog,currentUser));


//      todo  原来如此 函数上面的参数是从前端得到的，而且这些 都是往下面service传递的。这就是要对上接口的原因
//        todo  code 记得删掉再加上b21 ，直接return 。
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model,HttpSession session) {
//
        User currentUser = (User) session.getAttribute("user");
//        Long uid = currentUser.getId();
        model.addAttribute("page", blogService.blogssearch(pageable, blog,currentUser));
        return "admin/blogs :: blogList";
    }

//    @PostMapping("/blogs/search")
//    public String blogssearch(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
//                        Pageable pageable, BlogQuery blog, Model model ,HttpSession session) {
////
//        User currentUser = (User) session.getAttribute("user");
////
////
//        model.addAttribute("page", blogService.blogssearch(pageable, blog));
////        System.out.println("到底返回了吗 ？？");
//        return "admin/blogs::bloglist";
//    我草 不会是大小写l 没写好吧我去 L l 不分了
//    }
////    todo 只更新片段的 返回渲染方案


//增加 blog 的获得一个空框
    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        //要给框框传值你
        Blog blog = new Blog();

        model.addAttribute("blog", new Blog());

                return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

//改 blog 的获得当前blog先
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model,HttpSession session) {
        Blog blog= blogService.getBlog(id);

        System.out.println("  public String editInput(@PathVariable Long id, Model model,HttpSession session) {调用了" );
        User currentUser = (User) session.getAttribute("user");
        if( currentUser.getId().equals(    blog.getUser().getId() ) || currentUser.getType() == 1)  {


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


//增 和改 blog的推送
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
//        System.out.println(blog.getUser().getId());
//        System.out.println(blog.getFirstPicture());
        if(blog.getFirstPicture().equals("1")){

//            if  首图地址写1 就会用下面这个代替他
            blog.setFirstPicture("https://img1.baidu.com/it/u=314735915,3692012565&fm=253&fmt=auto&app=138&f=JPEG?w=417&h=260");
        }

        User currentUser = (User) session.getAttribute("user");

        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));


//        如果作者不等于当前作者，就是只能把文章变得不可展示，
//        1 作者空 直接赋值 2 作者等于当前账号 照常 3 作者不等于当前作者 ， 首先 那么只可能是管理员才能进入 blog input，执行这个post 操作，


  //  还要判断是不是新建，新建 的统一按照session user 当前用户，。不是新建立的，那么用以前建立的作者
        Blog b;
        if (blog.getId() == null) {
            blog.setUser( currentUser);

            b =  blogService.saveBlog(blog);
        } else {
            Blog existingBlog =   blogService.getBlog(blog.getId());
            blog.setUser(existingBlog.getUser() );
//            啊呀光想着怎么从前端传进来了，其实 可以再去后端用id 找到这个blog赋值啊
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
       if( currentUser.getId().equals(    blog.getUser().getId() ) || currentUser.getType() ==2){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
       }
       else {  attributes.addFlashAttribute("message", "删除失败");


       } return "/error/noright";
    }


}











