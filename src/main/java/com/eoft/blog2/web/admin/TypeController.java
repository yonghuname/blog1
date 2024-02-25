package com.eoft.blog2.web.admin;


import com.eoft.blog2.po.Type;
import com.eoft.blog2.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.Valid;



@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;
/*@GetMapping("/types")
这个方法用于处理对/types路径的GET请求。它接收两个参数：

Pageable pageable：这是一个Spring Data的接口，用于封装分页信息，如请求的页码、每页显示的元素数量等。在这个例子中，@PageableDefault注解被用来设置默认的分页参数，即每页显示10条记录，并且按照id字段降序排序。
Model model：这是一个用于在控制器和视图之间传递数据的对象。
在这个方法内部，首先调用typeService.listType(pageable)，这可能是一个服务层的方法，用于根据分页参数获取数据。然后，将获取的数据添加到模型中，键为"page"

@GetMapping("/types/input")
这个方法用于处理对/types/input路径的GET请求。它只接收一个参数：

Model model：同样用于在控制器和视图之间传递数据。
在这个方法内部，它向模型中添加了一个名为"type"的属性，这个属性是一个新创建的Type对象。这通常用于在创建新记录的表单页面中提供一个空的表单模型。然后，返回字符串"admin/types-input"，这同样对应于一个视图模板的名称，这个模板可能是用于输入新类型的表单页面。*/
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                        Pageable pageable, Model model) {
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }
    @PostMapping("/types")
    public String post(@Validated Type type, BindingResult result, RedirectAttributes attributes) {
      /*   Type type1 = typeService.getTypeb(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
*/
        Type t = typeService.saveType(type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
//        重定向才会又查询的数据
        return "redirect:/admin/types";
    }

/*
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }



    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

*/
}
