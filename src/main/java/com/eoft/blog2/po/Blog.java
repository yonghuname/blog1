package com.eoft.blog2.po;

import java.awt.print.PrinterGraphics;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_blog")

public class Blog {
    @Id
    @GeneratedValue
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
    private Date createTime;


}















