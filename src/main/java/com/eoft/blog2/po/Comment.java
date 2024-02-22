package com.eoft.blog2.po;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private  String avatar ;
    private String nickname;
    private String email;
    private  String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private Blog blog;
    //不打算写的  评论区
     @OneToMany
    private List<Comment> replyComment = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;







}
