package com.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	List<TodoEntity> findByTitle(String title);
	
	List<TodoEntity> findByTitleLike(String title);
	
	List<TodoEntity> findByTitleAndMemo(String title, String memo);
	
	// SQL語句：select * from todo where due_date between ? and ?
	List<TodoEntity> findByDueDateBetween(Date startDate, Date endDate);
	
	// SQL語句：select * from todo where id in (?, ?, ?);
	List<TodoEntity> findByIdIn(List<Long> ids);
	
	List<TodoEntity> findByTitleOrderByDueDateDesc(String title);
	
	List<TodoEntity> findAllByOrderByDueDate();
}
