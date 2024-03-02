package com.eoft.blog2.service;
import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.User;
import com.eoft.blog2.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by limi on 2017/10/20.
 */
public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);
    Page<Blog> blogssearch(Pageable pageable, BlogQuery blog, User user);

    Page<Blog> listBlog2( Long typeid,Pageable pageable);
    Blog getAndConvert(Long id);
    Page<Blog> listBlog(String query,Pageable pageable);
    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog3(Long tagId,Pageable pageable);
    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
