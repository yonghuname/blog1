package com.eoft.blog2.po;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
//    关系维护方
    private User sender;

    @OneToOne
    private User  receiver;
    @Basic(fetch = FetchType.LAZY)
    private  String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

//    public Message(User sender,User receiver, String content, Date timestamp) {
//        this.sender = sender;
//        this.receiver=receiver;
//        this.content = content;
//        this.timestamp = timestamp;
//    }
}
