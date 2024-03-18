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
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String username;  //login name
    private String password;
    private String avatar;
    private String email;
    private Integer type;//当 admin 和普通用户来处理 了
    private String slogan;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


    @OneToMany(mappedBy = "user")
//    被维护之间的关系
    private List<Blog> blogs =new ArrayList<>();


}
