package com.eoft.blog2.dao;


import com.eoft.blog2.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;



    public interface TypeRepository extends JpaRepository<Type,Long> {

        Type findByName(String name);
    }

