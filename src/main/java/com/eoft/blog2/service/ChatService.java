package com.eoft.blog2.service;

import com.eoft.blog2.po.Message;
import com.eoft.blog2.dao.MessageRepository;
import com.eoft.blog2.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;


public interface ChatService {

        Message saveMessage(Message message );

      List<Message> getAllMessages();

      List<Message> getHistoryMessagesFromTwo(Long sendid,Long rid);
}
