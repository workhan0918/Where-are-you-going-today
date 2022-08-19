package com.varxyz.wgt.map.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.wgt.map.domain.Map;

public class MapDao {
	private JdbcTemplate jdbcTemplate;
	
	public MapDao(DataSource dataSourceConfig) {
		jdbcTemplate = new JdbcTemplate(dataSourceConfig);
	}
//	public List<Map> search(String name) {
//		String sql = "SELECT * FROM test WHERE name = ?";
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class), name);
//	}
	
	public List<Map> findAll(){
		String sql = "SELECT * FROM test";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class));
	}
	
	public List<Map> search(String name){
		String sql = "SELECT * FROM test WHERE name like '%" + name +"%' ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Map>(Map.class));
	}
}
