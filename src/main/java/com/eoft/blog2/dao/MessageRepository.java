package com.eoft.blog2.dao;

import com.eoft.blog2.po.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    @Query("SELECT m FROM Message m WHERE (m.sender.id = :sendId AND m.receiver.id = :receiverId) OR (m.sender.id = :receiverId AND m.receiver.id = :sendId) ORDER BY m.timestamp ASC")
    List<Message> findTwoHistory(Long sendId, Long receiverId);

}
