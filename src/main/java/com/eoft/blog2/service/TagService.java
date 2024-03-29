package com.eoft.blog2.service;

import com.eoft.blog2.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {

    Tag saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    Tag updateTag(Long id, Tag type);

    List<Tag> listTagTop(Integer size);
//    给主页侧边栏使用
    void deleteTag(Long id);
}
