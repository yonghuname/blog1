package com.eoft.blog2.po;

import java.awt.print.PrinterGraphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_blog")

public class Blog {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private  String title;
    private  String content;

    private String firstPictrue;
    private String flag;
    private Integer views;
    private boolean appriciation;
    private boolean shareStatement;
    private boolean commentabled;

    private boolean published;
    private boolean recommend;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
//    主动维护关系
    private  Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne
//    关系维护方
    private  User user;

    @OneToMany( mappedBy = "blog")
// 关系被维护方加上 mapper

    private List<Comment> comments = new ArrayList<>();


}















