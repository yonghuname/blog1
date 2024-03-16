package com.eoft.blog2.web.admin;


import com.eoft.blog2.po.Tag;
import com.eoft.blog2.po.User;
import com.eoft.blog2.service.TagService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;
//常规查询
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }
// 手册进入输入框
    @GetMapping("/tags/input")
    public String input(Model model ,HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        if( currentUser.getType()==1) {

            model.addAttribute("tag", new Tag());
            return "admin/tags-input";
        } else return "/error/noright";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model ,HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if( currentUser.getType()==1)  {

            model.addAttribute("tag", tagService.getTag(id));
            return "admin/tags-input";
        } else return "/error/noright";
    }


    @PostMapping("/tags")
    public String post(@Valid Tag tag,BindingResult result, RedirectAttributes attributes,HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if( currentUser.getType()==1)  {
            Tag tag1 = tagService.getTagByName(tag.getName());
            if (tag1 != null) {
                result.rejectValue("name", "nameError", "不能添加重复的分类");
            }
            if (result.hasErrors()) {
                return "admin/tags-input";
            }
            Tag t = tagService.saveTag(tag);
            if (t == null) {
                attributes.addFlashAttribute("message", "新增失败");
            } else {
                attributes.addFlashAttribute("message", "新增成功");
            }
            return "redirect:/admin/tags";
        }else return "/error/noright";
    }


    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes,HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if( currentUser.getType()==1) {
            Tag tag1 = tagService.getTagByName(tag.getName());
            if (tag1 != null) {
                result.rejectValue("name", "nameError", "不能添加重复的分类");
            }
            if (result.hasErrors()) {
                return "admin/tags-input";
            }
            Tag t = tagService.updateTag(id, tag);
            if (t == null) {
                attributes.addFlashAttribute("message", "更新失败");
            } else {
                attributes.addFlashAttribute("message", "更新成功");
            }
            return "redirect:/admin/tags";
        } else return "/error/noright";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes,HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if( currentUser.getType()==1) {
            tagService.deleteTag(id);
            attributes.addFlashAttribute("message", "删除成功");
            return "redirect:/admin/tags";
        } else return "/error/noright";
    }


}
