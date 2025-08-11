package com.course.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dao.TodoDao;
import com.course.model.TodoDto;
import com.course.model.TodoVo;

@Service
public class TodoService {
	
	@Autowired
	private TodoDao todoDao;
	
	@Autowired
	private TodoServiceHelper helper;
	
	public List<TodoVo> findAllTodo() {
		List<TodoDto> dtoList = todoDao.findAll();
		List<TodoVo> voList  = dtoList.stream().map(d -> helper.convertToVo(d)).collect(Collectors.toList());

		return voList;
	}
}
