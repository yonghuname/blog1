package com.eoft.blog2.service;


import com.eoft.blog2.dao.BlogRepository;
import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.Type;
import com.eoft.blog2.po.User;
import com.eoft.blog2.util.MarkdownUtils;
import com.eoft.blog2.vo.BlogQuery;
import com.eoft.blog2.web.NoFoundException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);

    }

    public Blog getAndConvert(Long id){
        Blog blog = blogRepository.getOne(id);

        if (blog == null) {
            throw new NoFoundException("此文章不存在");
        }
        if (  blog.getViews()==null) {
            blog.setViews(0);
        } else {
            blog.setViews(blog.getViews() + 1);
        }
//        Blog b = new Blog();
//        BeanUtils.copyProperties(blog,b);
//        String content = b.getContent();
//        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

//        blogRepository.updateViews(id);
        return blog;
    }
@Override
public Page<Blog> listBlog(String query,Pageable pageable){
        return blogRepository.findbyQuery(query,pageable);
}
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Pageable pageable =   PageRequest.of(0, size, Sort.by(Sort.Direction.DESC,"updateTime"));
        return blogRepository.findTop(pageable);
    }


    /*
    @Override
    Page<Blog> listBlog(Pageable pageable, Integer typeid){
        Pageable pageable =   PageRequest.of(0, typeid, Sort.by(Sort.Direction.DESC,"updateTime"));
        return blogRepository.findByTAGid(pageable);
    };
    */
// 这个是后台管理的查询，不要和前台查询 弄混了，改了半天最后发现是改后台
    public Page<Blog> blogssearch(Pageable pageable, BlogQuery blogQuery , User currentUser) {
        if (blogQuery == null) {
            return blogRepository.findAll(pageable); // 查询全部
        }
        Long uid = currentUser.getId();
        String title = blogQuery.getTitle();
        Long typeId = blogQuery.getTypeId();
        Boolean recommend = blogQuery.isRecommend();
        System.out.println(title); System.out.println(typeId); System.out.println(recommend);

        // 检查是否所有条件都为空
        if (StringUtils.isBlank(title) && typeId == null && recommend == false) {
           if(currentUser.getType()==1) return blogRepository.findAll(pageable);
           else  return blogRepository.findByUID(pageable,uid); //          如果是管理员类型 的账户就全搜索.这是没初试条件的搜索
        }
        {
            if(currentUser.getType()==1) return blogRepository.findByTitleTypeRecommend(title, typeId, recommend,pageable);
         else return  blogRepository.findByTitleTypeRecommendUid(title, typeId, recommend, pageable,uid);
  }
    }




    @Override
    public Page<Blog> listBlog(Pageable pageable){        return blogRepository.findbyPublished(pageable); }
    @Override
    public Page<Blog> listBlog2(Long typeid , Pageable pageable ){
//        Pageable pageable =   PageRequest.of(0, typeid, Sort.by(Sort.Direction.DESC,"updateTime"));
        return blogRepository.findByTypeid
                (  typeid,pageable);
    };

    @Override
    public Page<Blog> listBlog3(Long tagid, Pageable pageable)


    {   return blogRepository.findByTagid
            (  tagid,pageable);
    }


    @Transactional
    @Override
//    新建
    public Blog saveBlog(Blog blog) {
//        初次创造就设置初始化
        if (blog.getCreateTime() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
//        System.out.println("-*------save blog  ");
//        if(blog.getFirstPicture() =="1"){
//            System.out.println("-*------首图是1 进行转化");
//            blog.setFirstPicture("https://img1.baidu.com/it/u=314735915,3692012565&fm=253&fmt=auto&app=138&f=JPEG?w=417&h=260");
//        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
//    更新
    public Blog updateBlog(Long id, Blog blog) {

//        System.out.println("-*------update blog  ");
        Blog b = blogRepository.getOne(id) ;
        if (b == null) {
            throw new NoFoundException("该博客不存在");
        }

        BeanUtils.copyProperties(blog,b
//                , MyBeanUtils.getNullPropertyNames(blog)
        );
        b.setUpdateTime(new Date());

        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
















