package com.eoft.blog2.web.admin;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.UserService;
import com.eoft.blog2.web.NoFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    public UserService userService;
    @GetMapping("/user")
    public String userget(HttpSession session, Model model){
        User user =  (User) session.getAttribute("user");
        model.addAttribute("avatar",user.getAvatar());
        model.addAttribute("nickname",user.getNickname());
        if(user.getSlogan()!=null)     model.addAttribute("slogan",user.getSlogan());
        return "/admin/useredit";
    }
    @PostMapping("/user")
    public String userpost(HttpSession session, String password1,String avatar,String nickname,String slogan ){
//        userService.
        User user =  (User) session.getAttribute("user");
            Long id = user.getId();
        System.out.println(id);
             User user2 =   userService.updateUser(password1,nickname,id,avatar,slogan);
        System.out.println("用户新的id是"+user2.getNickname());
         if(user2!= null ) {
             System.out.println(23333);
             user2.setPassword(null);
             session.setAttribute("user",user2);
             return "redirect:/admin/index";
         }
         else{

             throw new NoFoundException("用户 没找到.");
         }


    }
}
