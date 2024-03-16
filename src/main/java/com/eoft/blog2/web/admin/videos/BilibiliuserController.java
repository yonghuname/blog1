package com.eoft.blog2.web.admin.videos;


import com.eoft.blog2.po.Todoitem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/media")
public class BilibiliuserController {

    @GetMapping("/bilibiliuser")
    public String getlist ( ) {
//        RequestBody Bililist } bililist, HttpSession session, Model model) {
        return "/admin/media/bilibiliuser";
    }

    @PostMapping("/bilibiliuser")
    public String postlist ( ) {
//        RequestBody Bililist } bililist, HttpSession session, Model model) {
        return "/admin/media/bilibiliuser";
    }
    }
