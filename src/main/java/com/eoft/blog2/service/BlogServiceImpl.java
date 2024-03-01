package com.eoft.blog2.service;


import com.eoft.blog2.dao.BlogRepository;
import com.eoft.blog2.po.Blog;
import com.eoft.blog2.po.Type;
import com.eoft.blog2.util.MarkdownUtils;
import com.eoft.blog2.vo.BlogQuery;
import com.eoft.blog2.web.NoFoundException;
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
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

//        blogRepository.updateViews(id);
        return b;
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


    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
//                    todo 需要了解怎么办弄这个 这个是什么意思 这里是模糊查询 ，把本类的所有注释完成了解
                }
//md 屎山化了。又new一个包vo
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId() ));
                }


                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
//实际上是三个栏目的需求
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
//                这对于 cq cb不知道是什么联合查询吗 ？
                return null;
            }
        },pageable);
    }
    @Override
    public Page<Blog> listBlog(Pageable pageable){        return blogRepository.findAll(pageable); }








    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
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
















