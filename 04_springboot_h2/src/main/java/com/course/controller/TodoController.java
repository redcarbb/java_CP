package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.model.TodoDto;
import com.course.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/add")
	public String addTodo() {
		todoService.addTodo();
		return "index";
	}
	
	@GetMapping("/update")
	public String updateTodo() {
		todoService.updateTodo();
		return "index";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Long id) {
		todoService.deleteById(id);
		return "index";
	}
	
	@GetMapping("/todoList")
	public String getAllTodo() {
		todoService.findAllTodo();
		return "index";
	}
	
	@GetMapping("/title")
	public String getTodoByTitle(@RequestParam String keyword) {
		List<TodoDto> dtoList = todoService.findByTitle(keyword);
		for (TodoDto dto : dtoList) {
			System.out.println(dto);
		}
		return "index";
	}
	
}
