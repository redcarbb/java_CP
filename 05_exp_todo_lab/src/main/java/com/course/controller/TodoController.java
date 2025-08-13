package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.course.model.TodoVo;
import com.course.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
    @GetMapping("/")
    public String home(Model model) {
    	List<TodoVo> todoList = todoService.findAllTodo();
    	model.addAttribute("todoList", todoList);
        return "index";
    }
    
    @ModelAttribute("title")
    public String title() {
    	return "待辦事項222";
//    	return "<script>alert('!!!!!!')</script>";
    }
    
    @GetMapping("/toAddPage")
    public String toAddPage(@ModelAttribute("todoObj") TodoVo todo) {
    	return "addTodoPage";
    }
    
    @PostMapping("/todo")
    public String addTodo(@Valid @ModelAttribute("todoObj") TodoVo todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// 有欄位檢核錯誤，回到新增頁面
			return "addTodoPage";
		}

		todoService.addTodo(todo);
		// 新增完畢後，轉導至首頁，避免refresh重送新增
		return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String delTodo(@PathVariable Long id) {
    	todoService.deleteTodo(id);
    	return "redirect:/";
    }
    
    @GetMapping("/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable Long id, Model model) {
    	TodoVo vo = todoService.getTodoById(id);
    	model.addAttribute("todoObj", vo);
    	return "editTodoPage";
    }
    
    @PostMapping("/editTodo")
    public String editTodo(@ModelAttribute("todoObj") TodoVo todo) {
    	
    	todoService.editTodo(todo);
    	return "redirect:/";
    }
    
    
}
