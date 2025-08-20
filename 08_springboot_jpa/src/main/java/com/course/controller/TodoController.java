package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Operation(summary = "新增待辦事項(save)", tags = "JPA 原生方法")
	@PostMapping("/todo")
	public TodoEntity addTodo(@RequestBody TodoEntity entity) {
		return todoService.addTodo(entity);
	}
	
	@Operation(summary = "刪除待辦事項(deleteById)", tags = "JPA 原生方法")
	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
	}
	
	@Operation(summary = "更新待辦事項(save)", tags = "JPA 原生方法")
	@PatchMapping("/todo")
	public TodoEntity updateTodo(@RequestBody TodoEntity entity) {
		return todoService.updateTodo(entity);
	}
	
	@Operation(summary = "取得待辦事項(findByTitle)", tags = "Query Method ")
	@GetMapping("/todo/{title}")
	public List<TodoEntity> getTodoListByTitle(@PathVariable String title) {
		return todoService.getTodoByTitle(title);
	}
	
	@Operation(summary = "取得待辦事項(findByTitleLike)", tags = "Query Method")
	@GetMapping("/todo-like/{title}")
	public List<TodoEntity> findByTitleLike(@PathVariable String title) {
		return todoService.getTodoByTitleLike(title);
	}
	
	@Operation(summary = "取得待辦事項(findByDueDateBetween)", tags = "Query Method")
	@GetMapping("/todo-between/")
	public List<TodoEntity> getTodoByDueDateBetween(@RequestParam String startDate, @RequestParam String endDate) {
		return todoService.getTodoByDueDateBetween(startDate, endDate);
	}
	
	@Operation(summary = "取得待辦事項(findByIdIn)", tags = "Query Method")
	@GetMapping("/todo-in/")
	public List<TodoEntity> getTodoByDueDateBetween() {
		return todoService.getTodoIn();
	}
	
	@Operation(summary = "取得待辦事項(findByCondition)", tags = "Query")
	@GetMapping("/todo-query/{title}")
	public List<TodoEntity> getTodoByCondition(@PathVariable String title) {
		return todoService.getTodoByTitleQuery(title);
	}
	
	@Operation(summary = "修改待辦事項標題(updateTodoTitle)", tags = "@Query")
	@PostMapping("/todo-title-update/{id}/{title}")
	public ResponseEntity<String> updateTodoTitle(@PathVariable Long id, @PathVariable String title) {
		todoService.updateTodoTitle(id, title);
		return ResponseEntity.ok("OK");
	}
	
	@Operation(summary = "取得所有待辦事項(分頁)(findAll)", tags = "分頁")
	@GetMapping("/todos-page/{pageNumber}/{pageSize}")
	public Page<TodoEntity> getAllTodoListPageable(@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
		return todoService.getAllTodoPageable(pageNumber, pageSize);
	}
	
	@Operation(summary = "取得所有待辦事項2(分頁)(findAll)", tags = "分頁")
	@GetMapping("/todos-page2")
	public Page<TodoEntity> getAllTodoListPageable2(@RequestParam String title, Pageable pageable) {
		return todoService.getAllTodoPageable2(pageable);
	}
	
}
