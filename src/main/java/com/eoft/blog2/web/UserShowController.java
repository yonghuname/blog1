package com.eoft.blog2.web;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.UserService;
import com.eoft.blog2.web.NoFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserShowController {

    @Autowired
    public UserService userService;
    @Autowired
    public BlogService blogService;
    @GetMapping("/user/{id}")
    public String userget(@PathVariable Long id, Model model, @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
    Pageable pageable){
        User user = userService.getbyid(id);
        System.out.println(user.getNickname());
        user.setPassword("");


        model.addAttribute("user",user);



        model.addAttribute("page",blogService.listmyshowBlog(user.getId(),pageable));
        return "user";
    }
}
