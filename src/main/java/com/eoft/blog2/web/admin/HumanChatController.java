package com.eoft.blog2.web.admin;

import com.eoft.blog2.po.User;
import com.eoft.blog2.service.ChatService;

import com.eoft.blog2.po.Message;
import com.eoft.blog2.service.UserService;
import com.eoft.blog2.web.NoFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class HumanChatController {

    private final ChatService chatService;

    @Autowired
    public HumanChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/chat/{receiverId}")
    public String getChatPage(@PathVariable("receiverId") Long receiverId, Model model, HttpSession session) {
        // 获取当前登录用户信息（发送方）
        User currentUser = (User) session.getAttribute("user");
Long senderId = currentUser.getId();
        // 将发送方和接收方的ID传递到页面
        model.addAttribute("senderId", senderId);
        model.addAttribute("receiverId", receiverId);

        // 获取所有历史消息并传递给前端
        List<Message> messages = chatService.getHistoryMessagesFromTwo(senderId,receiverId);

        model.addAttribute("messages", messages);
        return "/admin/humanchat"; // 返回Thymeleaf模板的路径，例如：/admin/humanchat.html
    }
}