package kr.ac.hansung.cse.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Activity;

@Repository
public class ActivityDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		 jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public Activity getActivities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
