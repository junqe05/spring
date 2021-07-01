package com.example.demo.controller;

import java.sql.PreparedStatement;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.UserVO;


@Controller
@RequestMapping("/user") //클래스에는 get, post 모두를 포함하는 requestMapping을 태그할수 있다.
public class UserController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/get") //http://localhost:8080/user/get?num=3
	public @ResponseBody String select1(@ModelAttribute("num") int num) {
		String sql = "SELECT * FROM member WHERE num=?";
		UserVO vo =  jdbcTemplate.queryForObject(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)),
				num);
		return vo.getName();
		/* 
		* jdbcTemplate.queryForObject(sql, 익명함수, num);
		* 
		* rs : rs get	
		*  new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)): 익명함수의 바디
		*  JAVA의 익명함수 구조 (rs,i) -> body , (rs,i)는 파라미터
		*  i: 루프를 돌릴때 필요한 인자
		*  queryForObject: 한개의 오브젝트만 리턴함
		*/
	}
	@GetMapping("/user/get/{num}") //http://localhost:8080/user/get/3
	public @ResponseBody String select2(@PathVariable("num") int num) {
		String sql = "SELECT * FROM member WHERE num=?";
		UserVO vo =  jdbcTemplate.queryForObject(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)),
				num);
		return vo.getName();
	}
	
	@GetMapping("/user/count") //http://localhost:8080/user/count
	public @ResponseBody String count() {
		String sql = "SELECT * FROM member";
		List<UserVO> vo =  jdbcTemplate.query(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		
		return vo.size()+"";
	}
	
	@GetMapping("/user/add") //http://localhost:8080/user/add?name=addtest&phone=010-1234-1234&email=addtest@addtest.com
	public String insert(@ModelAttribute("usr") UserVO u , ModelMap model) {
		//String sql = "INSERT INTO member VALUES(NULL,?,?,?)"; // NULL은 자동 증가 필드
		String sql = "INSERT INTO member(name,phone,email) VALUES(?,?,?)"; 
		int n =  jdbcTemplate.update(sql,u.getName(), u.getPhone(), u.getEmail()); 
		//update : 데이터를 변경 시킬때 쓰는 메소드
		//리턴값: 영향을 받은 행의 수
		
		model.put("user",u);
		//return n>0?"OK":"Failed";
		return "addResult";
	}
	
	@GetMapping("/user/update") //http://localhost:8080/user/update?num=6&phone=010-1111-1111
	public String update(@ModelAttribute("num") UserVO u , ModelMap model) {
		
		String sql = "UPDATE member SET phone = ? WHERE num = ? "; 
		int n =  jdbcTemplate.update(sql,u.getPhone(), u.getNum()); 


		sql = "SELECT * FROM member WHERE num = ?";
		
		List<UserVO> vo =  jdbcTemplate.query(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))
				,u.getNum());
		
		model.addAttribute("users",vo);
		//return n>0?"OK":"Failed";
		
		

		return "update";
	}

	@GetMapping("/user/delete") //http://localhost:8080/user/delete?num=6
	public String delete(@ModelAttribute("num") int num, ModelMap model) {
		
		String sql = "SELECT * FROM member WHERE num = ?";
		List<UserVO> vo =  jdbcTemplate.query(sql, 
				(rs,i) -> new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))
				,num);
		
		model.addAttribute("users",vo);
		
		sql = "DELETE FROM member WHERE num = ?";
		int n = jdbcTemplate.update(sql,num);
		
		model.addAttribute("delete",n);

		return "delete";
	}
	
	@GetMapping("/user/add2") //http://localhost:8080/user/add2?name=add2test&phone=010-1234-1234&email=add2test@addtest.com
	public String add2(@ModelAttribute("usr") UserVO vo, ModelMap model) {
		String sql = "INSERT INTO member (name.phone,eamil) VALUES(NULL,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn->{
							PreparedStatement pstmt;
							pstmt = conn.prepareStatement(sql, new String[] {"num"});
							pstmt.setString(1, vo.getName());
							pstmt.setString(2, vo.getPhone());
							pstmt.setString(3, vo.getEmail());
							return pstmt;
				}, keyHolder);
		
		vo.setNum(keyHolder.getKey().intValue());
		
		model.addAttribute("user",vo);
		return "addResult";

	}
	
	

	
	  
	  
	
	
	
	
	@GetMapping("/index")
	public @ResponseBody String index() {
		return "Index";
	}

	@GetMapping("/read")
	public @ResponseBody String read() {
		return "Read";
	}
}
