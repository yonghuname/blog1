package com.eoft.blog2.service;


import com.eoft.blog2.po.Type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface TypeService {

    List<Type> listTypeTop(Integer size);

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();
    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
