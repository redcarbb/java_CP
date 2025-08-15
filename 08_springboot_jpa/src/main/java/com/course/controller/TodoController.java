package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.entity.TodoEntity;
import com.course.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@Operation(summary = "取得所有待辦事項(findAll)", tags = "JPA 原生方法")
	@GetMapping("/todos")
	public List<TodoEntity> getAllTodoList() {
		return todoService.getAllTodo();
	}
}
