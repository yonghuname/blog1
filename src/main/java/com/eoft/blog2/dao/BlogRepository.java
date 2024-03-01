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

    @Query("SELECT b FROM Blog b WHERE (b.title LIKE ?1 OR b.content LIKE ?1) AND b.published = true")
    Page<Blog> findbyQuery(String query, Pageable page);
    @Query("SELECT b FROM Blog b WHERE b.published = true")
    Page<Blog> findbyPublished(Pageable pageable);
    @Query("SELECT b FROM Blog b WHERE b.type.id = :typeid AND b.published = true")
    Page<Blog> findByTypeid(Long typeid, Pageable pageable);
// todo 关于复杂情况 jpa 注解 实体关系可以偷苹果实体 .实体.属性呆滞，因为 有些是属性 是 jpa 比如 type_id 虚拟维护的

   @Query("SELECT b FROM Blog b JOIN b.tags t WHERE  b.published = true  and t.id= :tagid  ")
    Page<Blog> findByTagid(Long tagid, Pageable pageable);

}
//    ?1 是第一个 参数的意思






