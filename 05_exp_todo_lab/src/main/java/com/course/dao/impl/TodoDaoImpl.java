package com.course.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		jdbcTemplate.update(sql, dto.getTitle(), dto.getDueDate(), dto.getStatus(), dto.getId());
		
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM TODO WHERE ID =　? ";
		jdbcTemplate.update(sql, id);
		
	}

	@Override
	public List<TodoDto> findAll() {
		String sql = "SELECT * FROM TODO";
		RowMapper<TodoDto> rowMapper = new RowMapper<>() {
			@Override
			public TodoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				TodoDto dto = new TodoDto();
				dto.setId(rs.getLong("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setDueDate(rs.getDate("DUE_DATE"));
				dto.setStatus(rs.getInt("STATUS"));
				dto.setMemo(rs.getString("MEMO"));
				return dto;
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<TodoDto> findByTitle(String title) {
		
		List<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		// StringBuffer sb2 = new StringBuffer();
		
		sb.append(" SELECT ");
		sb.append(" T.ID, ");
		sb.append(" T.TITLE, ");
		sb.append(" T.DUEDATE, ");
		sb.append(" T.STATUS ");
		sb.append(" FROM TODO T ");
		// sb.append(" WHERE 1 = 1 ");
		sb.append(" WHERE T.ID IS NOT NULL ");
		if (!title.isBlank()) {
			sb.append(" AND T.TITLE LIKE ? ");	
			params.add("%" + title + "%");
		}
//		sb.append(" AND T.TITLE1 = ? ");
//		sb.append(" AND T.TITLE2 = ? ");
		
		RowMapper<TodoDto> rowMapper = (rs, rowNum) -> {
			TodoDto dto = new TodoDto();
			dto.setId(rs.getLong("ID"));
			dto.setTitle(rs.getString("TITLE"));
			dto.setDueDate(rs.getDate("DUEDATE"));
			dto.setStatus(rs.getInt("STATUS"));
			return dto;
		};
		return jdbcTemplate.query(sb.toString(), rowMapper, params.toArray());
	}
}
