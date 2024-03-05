package com.eoft.blog2.web.admin;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class UserController {
    public UserService userService;
    @GetMapping("/user")
    public String userget(HttpSession session, Model model){
        User user =  (User) session.getAttribute("user");
        model.addAttribute("user",user);

        return "admin/user";
    }
    @PostMapping("/user/done")
    public String userpost(HttpSession session, Model model){

        return "admin/index";
    }
}
