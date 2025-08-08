package com.course.dao;

import java.util.List;

import com.course.model.TodoDto;

public interface TodoDao {
	/**
	 * 新增
	 * @param dto
	 */
	void add(TodoDto dto);
	
	/**
	 * 修改
	 * @param dto
	 */
	void update(TodoDto dto);
	
	/**
	 * 刪除
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 查詢全部
	 * @return
	 */
	List<TodoDto> findAll();
	
	/**
	 * 依標題查詢
	 * @param title
	 * @return
	 */
	List<TodoDto> findByTitle(String title);
}
