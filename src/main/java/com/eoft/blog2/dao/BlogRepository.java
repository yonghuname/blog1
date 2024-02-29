package com.eoft.blog2.dao;

import com.eoft.blog2.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {


    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);


    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findbyQuery(String query,Pageable page);

}
//    ?1 是第一个 参数的意思






