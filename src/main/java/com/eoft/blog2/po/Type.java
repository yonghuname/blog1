package com.eoft.blog2.po;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "分类名称不可以为空")
    private  String name;

    //    被维护之间的关系
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs =new ArrayList<>();
}












