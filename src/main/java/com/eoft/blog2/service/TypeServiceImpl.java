package com.eoft.blog2.service;


import com.eoft.blog2.dao.TypeRepository;
import com.eoft.blog2.po.Type;

import com.eoft.blog2.web.NoFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;



    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
//        getone代替了findone 新版

    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

/*
@Query(value = "select id,name from sys_type order by id :order limit :size", nativeQuery = true)
 Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogs.size"));
*/
    @Override
    public List<Type> listTypeTop(Integer size) {
        System.out.println("尝试起作用");
//        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogCountInType"));
        Pageable pageable = PageRequest.of(0, size, JpaSort.unsafe(Sort.Direction.DESC, "SIZE(blogs)"));
        System.out.println("-----------起作用");
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NoFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }


//    通过找id 来修改update



    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
