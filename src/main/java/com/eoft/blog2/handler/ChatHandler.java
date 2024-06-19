package com.eoft.blog2.handler;

import cn.hutool.json.JSONObject;
import com.eoft.blog2.dao.UserRepository;
import com.eoft.blog2.po.Message;
import com.eoft.blog2.po.User;
import com.eoft.blog2.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class ChatHandler extends TextWebSocketHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatService chatService;
    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("我起作用  了 222 - - --- - - - ");
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        System.out.println("我起作用  了  - - --- - - - ");
        String payload = textMessage.getPayload();
        JSONObject json = new JSONObject(payload);
        String content = json.getStr("content");

        // 从路径参数中获取 receiverId
        Long receiverId = Long.valueOf(session.getUri().getPath().split("/")[3]);
        Long senderId = Long.valueOf(session.getUri().getPath().split("/")[4]);
        System.out.println(receiverId );
        System.out.println( senderId );
        // 获取当前用户信息，假设从 session 中获取
        Principal principal = session.getPrincipal();


        // 在这里处理消息，保存到数据库或者转发给指定的接收者
        // 然后广播给所有连接的 WebSocket 客户端

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(new Date());
        // 假设这里 sender 是当前用户的 ID
        message.setSender(sender); // 假设 senderId 是发送者的 ID
        message.setReceiver(receiver); // 使用从路径参数中获取的 receiverId

        // 发送消息给接收者，假设在此处保存到数据库
        // chatService.saveMessage(message);
        chatService.saveMessage(message);

        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(message);
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(messageJson));
        }
    }
}