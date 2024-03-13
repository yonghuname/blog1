package com.eoft.blog2.po;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Entity // 表示这是一个JPA实体
@Table(name = "t_todos") // 指定数据库中的表名
@Data
@NoArgsConstructor
public class Todoitem {

    @Id // 表示这个字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略，自增
    private Long id;
    @Size(max = 250)
    private  String title;
    private boolean finished;
    @Basic(fetch = FetchType.LAZY)
    private  String content;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date aimTime;

//
//    @ManyToMany(cascade = {CascadeType.PERSIST})
//    private List<Tag> tags = new ArrayList<>();
//    @ManyToOne
//    private  Type type;
//    @Transient
//    private String tagIds;
    @ManyToOne
//    关系维护方
    private  User user;
//
//    public void init() {
//        this.tagIds = tagsToIds(this.getTags());
//    }
//
//    private String tagsToIds(List<Tag> tags) {
//        if (!tags.isEmpty()) {
//            StringBuffer ids = new StringBuffer();
//            boolean flag = false;
//            for (Tag tag : tags) {
//                if (flag) {
//                    ids.append(",");
//                } else {
//                    flag = true;
//                }
//                ids.append(tag.getId());
//            }
//            return ids.toString();
//        } else {
//            return tagIds;
//        }
//    }
}
