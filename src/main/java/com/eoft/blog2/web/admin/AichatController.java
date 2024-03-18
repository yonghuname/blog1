package com.eoft.blog2.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AichatController {

    @GetMapping("/aichat")
    public String getaichat(){

        return "admin/aichat";
    }


}
