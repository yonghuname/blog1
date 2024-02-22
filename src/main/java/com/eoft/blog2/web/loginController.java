package com.eoft.blog2.web;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class loginController {


    @Autowired
    private UserService userService;


    @GetMapping
    public String loginPage(){
        return "managementview/login";
    }


    @PostMapping("/login")
    public  String login(@RequestParam String username,
                         @RequestParam String password,
                         HttpSession session, RedirectAttributes attributes
                         )
    {
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "managementview/index";
        } else{
            addtributes.addFlashAttribute()
            return "redirect:managementview";
        }


    }








}
