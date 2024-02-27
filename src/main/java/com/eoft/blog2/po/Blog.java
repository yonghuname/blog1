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
//todo。当你在类上使用 @Entity 注解时，你告诉 JPA 这个类应该被映射到数据库中的一个表。 所以 blogquery不用 entity  只用data设置getset 就可以了

public class Blog {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private  String title;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private  String content;

    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;

    private boolean published;
    private boolean recommend;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @ManyToOne
//    主动维护关系
    private  Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne
//    关系维护方
    private  User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
//    不想要设计 comment
    @Transient
    private String tagIds;

    // 关系被维护方加上 mapper
//    @OneToMany(mappedBy = "blog")
//    private List<Comment> comments = new ArrayList<>();


    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }


    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }



}















