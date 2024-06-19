package com.eoft.blog2.service;

import com.eoft.blog2.po.Message;
import com.eoft.blog2.dao.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eoft.blog2.po.User;
import java.util.Date;

import java.util.List;
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MessageRepository messageRepository;


    public Message saveMessage(User sender,User receiver, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(new Date());
        return messageRepository.save(message);
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll(); // 假设使用 JpaRepository 的 findAll() 方法获取所有消息
    }

    public List<Message> getHistoryMessagesFromTwo(Long sendid,Long rid){
        return messageRepository.findTwoHistory(  sendid,  rid);
    }

    public Message saveMessage(Message message ){
        return messageRepository.save(message);
    }

}
