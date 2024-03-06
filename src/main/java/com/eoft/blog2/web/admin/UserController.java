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
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

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

        return "admin/user";
    }
    @PostMapping("/user")
    public String userpost(HttpSession session, String password1,String avatar,String nickname){
//        userService.
        User user =  (User) session.getAttribute("user");
            Long id = user.getId();
        System.out.println(id);
             User user2 =   userService.updateUser(password1,nickname,id,avatar);
        System.out.println("用户新的id是"+user2.getNickname());
         if(user2!= null ) {
             user2.setPassword(null);
             session.setAttribute("user",user2);
         }
         else{
             throw new NoFoundException("用户 没找到.");
         }
        return "admin/index";
    }
}
