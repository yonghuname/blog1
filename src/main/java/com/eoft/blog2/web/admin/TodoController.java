    package com.eoft.blog2.web.admin;

    import com.eoft.blog2.po.Todoitem;
    import com.eoft.blog2.po.User;
    import com.eoft.blog2.service.TagService;
    import com.eoft.blog2.service.TodoService;
    import com.eoft.blog2.vo.BlogQuery;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.data.web.PageableDefault;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.Date;
    import java.util.List;

    @Controller
    @RequestMapping("/admin")
    public class TodoController {
        @Autowired
        private TodoService todoService;
        @GetMapping("/todolist")
        public String getTodolist(    Model model , HttpSession session){
            System.out.println("      public String getTodolist(    Model model , HttpSession session){" );
            User currentUser = (User) session.getAttribute("user");
           List<Todoitem> todoList = todoService.getalltodos(currentUser);
            Todoitem  currentTodoitem = new Todoitem();
            if(todoList.size()<0)
            {   currentTodoitem = todoList.get(0);}
            else
            {   currentTodoitem.setTitle("输入标题");
                currentTodoitem.setContent("输入内容 ");
                currentTodoitem.setFinished(false);
            }

           model.addAttribute("todolist",todoList);

            model.addAttribute("currentTodoitem",currentTodoitem);
            return "admin/todolist";
        }

        @PostMapping("/todolist/add")
        public String addTodo(@RequestBody Todoitem newtodo, HttpSession session, Model model){

//            System.out.println("   @PostMapping( todolist/add ");
            User currentUser = (User) session.getAttribute("user");
//        Todoitem newtodo = new Todoitem();
//        newtodo.setTitle(title);
            newtodo.setUser(currentUser);
            newtodo.setFinished(false);
            newtodo.setContent("");
            newtodo.setCreateTime(new Date());
            newtodo.setUpdateTime(new Date());

            todoService.Savetodos(newtodo);
            model.addAttribute("currentTodoitem",newtodo);
//            System.out.println("返回了吗");
            List<Todoitem> updatedTodos = todoService.getalltodos(currentUser);

            model.addAttribute("todolist", updatedTodos);
            return "admin/todolist";

        }
        @PostMapping("/todolist")
        public String updateTodo(@RequestBody Todoitem newtodo, HttpSession session, Model model){

            User currentUser = (User) session.getAttribute("user");
            //        Todoitem newtodo = new Todoitem();
            //        newtodo.setTitle(title);
            newtodo.setUser(currentUser);

            if(newtodo.getContent()==null)newtodo.setContent("");
            newtodo.setCreateTime(new Date());
            newtodo.setUpdateTime(new Date());

            todoService.Savetodos(newtodo);
            model.addAttribute("currentTodoitem",newtodo);
            System.out.println("返回了吗");
            List<Todoitem> updatedTodos = todoService.getalltodos(currentUser);

            model.addAttribute("todolist", updatedTodos);
            return "admin/todolist :: todolists";

        }

        @DeleteMapping("/todolist")
        public void deleteTodo( HttpSession session,@RequestParam("todoid")Long todoid,Model model) {
//            参数 id的名字要一样不然就映射
//            System.out.println("id"+id);
            User currentUser = (User) session.getAttribute("user");
            todoService.deleteById(currentUser,todoid);
            System.out.println("delete调用了 ");
//            return "redirect:/admin/todolist";
            Todoitem  currentTodoitem = new Todoitem();
            currentTodoitem.setTitle("输入标题");
            currentTodoitem.setContent("输入内容 ");
            currentTodoitem.setFinished(false);
            model.addAttribute("currentTodoitem",currentTodoitem);
//            todo 必须 处理当前的必须还是当前的。如果删掉当前的，不让删就完事了，回复一个你正在编辑 比其他好好处理

        }

    }
