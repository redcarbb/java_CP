package com.course.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.course.dao.TodoDao;
import com.course.model.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(TodoDto dto) {

		String sql = "INSERT INTO TODO (TITLE, DUEDATE, STATUS) VALUES (?, ?, ?) ";
		jdbcTemplate.update(sql, dto.getTitle(), dto.getDueDate(), dto.getStatus());
	}

	@Override
	public void update(TodoDto dto) {
		String sql = "UPDATE TODO SET TITLE = ?, DUEDATE = ?, STATUS = ? WHERE ID =　? ";
		jdbcTemplate.update(sql, dto.getTitle(), dto.getDueDate(), dto.getStatus(), dto.getStatus());
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TodoDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoDto> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
