package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // DB와 직접 연결하는 클래스
public class UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	String sql;
	
	public List<UserVO> selectList(){
		sql = "SELECT * FROM member";
		List<UserVO> voList =  jdbcTemplate.query(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		return voList;
	}
	
	public UserVO selectFromNum(int num){
		sql = "SELECT * FROM member WHERE num=?";
		UserVO vo =  jdbcTemplate.queryForObject(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)),
				num);
		return vo;
	}
	
	public boolean insert(UserVO vo){
		sql = "INSERT INTO member (name,phone,email) VALUES (?,?,?)";
		int insert =  jdbcTemplate.update(sql, vo.getName(), vo.getPhone(), vo.getEmail());
		return insert>0?true:false;
	}
	
	public boolean delete(int num){
		sql = "DELETE FROM member WHERE num=?";
		int delete =  jdbcTemplate.update(sql, num);
		return delete>0?true:false;
	}
	
	public boolean update(UserVO vo){
		sql = "UPDATE member  SET phone=? WHERE num=?";
		int update =  jdbcTemplate.update(sql, vo.getPhone(), vo.getNum());
		return update>0?true:false;
	}
	
	
	

}
