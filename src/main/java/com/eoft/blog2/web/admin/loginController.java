package com.eoft.blog2.web.admin;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.BlogService;
import com.eoft.blog2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping()
    public String loginPage(){

        return "admin/login";
    }
    @GetMapping("/login")
    public String loginPage2(){

        return "admin/login";
    }
    @PostMapping("/login")
    public  String login(@RequestParam String username,
                         @RequestParam String password,
                         HttpSession session,
                         RedirectAttributes attributes
                         )
    {
        User user = userService.checkUser(username,password);

        if(user != null){

//            System.out.println("登录在等待啊啊 啊啊啊啊 啊啊");
            user.setPassword(null);
            session.setAttribute("user",user);

//            System.out.println("登录成功了啊了哈哈哈哈哈哈哈 ");

            return "admin/index";
        } else{
            System.out.println("登录失败了了了了了了了");
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }








    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
    session.removeAttribute("user");
    return "redirect:/admin";

    }

//没有index 就用你了
public BlogService blogService;
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        System.out.println("底部渲染");
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

    @GetMapping("/register")
    public String getregister(){
        return "admin/register";
    }
    @PostMapping("/register")
    public String Posttoregist(@RequestParam String username,
                               @RequestParam String password1,
                               @RequestParam String email,
                               @RequestParam String invitecode,
                               @RequestParam String nickname,
                               Model  model){


        String errorMessage = userService.registerUser(email, username, password1,
                invitecode, nickname);

        if(errorMessage ==null) {

            model.addAttribute("message", "注册成功，请登录。");
            return "redirect:admin/login";
        }
        else {
            System.out.println("错误信息： "+errorMessage);
            model.addAttribute("message", errorMessage);
            model.addAttribute( "email",email);
            model.addAttribute( "username",username);
            model.addAttribute("password1",password1);
            model.addAttribute("invitecode",invitecode);
            model.addAttribute("nickname",nickname);
            System.out.println("这就是你的重定向吗 ？？？");

//            我擦
//            return "redirect:admin/register";
            return null;
        }



    }


}
