package com.eoft.blog2.dao;

import com.eoft.blog2.po.Todoitem;
import com.eoft.blog2.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TodoRepository extends JpaRepository<Todoitem,Long>  {
    @Query("select t from Todoitem t where t.user.id = :uid")
    List<Todoitem> findAllByUId(Long uid);
}
