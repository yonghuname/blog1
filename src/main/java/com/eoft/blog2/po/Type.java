package com.eoft.blog2.po;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "分类名称不可以为空1")
    private  String name;

    //    被维护之间的关系
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs =new ArrayList<>();

/*
    @Formula("(SELECT COUNT(*) FROM t_blog  b WHERE b.type_id = id)")
    private int blogCountInType;
    */

}












