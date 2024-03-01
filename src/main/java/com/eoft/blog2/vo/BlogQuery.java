package com.eoft.blog2.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

    }
