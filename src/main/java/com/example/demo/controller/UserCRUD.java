package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.UserDAO;
import com.example.demo.user.UserVO;



@Controller
@RequestMapping("/user2")
public class UserCRUD {
	
	@Autowired
	UserDAO dao;
	
	@GetMapping("/list") //http://localhost:8080/user2/list
	public String list(Model model) {
		List<UserVO> list = dao.selectList();
		model.addAttribute("list",list);
		return "list";
	}

	@GetMapping("/insert") //http://localhost:8080/user2/insert?name=usr2&phone=010-1234-1234&email=usr2@usr2.com
	public @ResponseBody String insert(@ModelAttribute("usr") UserVO vo) {
		return dao.insert(vo)?"true":"false";
	}

	@GetMapping("/delete") //http://localhost:8080/user2/delete?num=9
	public String delete(@ModelAttribute("num") int num, Model model) {
		boolean delete = dao.delete(num);
		if(delete) return list(model);
		else return "fail";
	}

	@GetMapping("/userview") //http://localhost:8080/user2/userview?num=1
	public String userView(@ModelAttribute("num") int num, Model model) {
		UserVO vo = dao.selectFromNum(num);
		model.addAttribute("user",vo);
		return "userView";
	}
	
	
	@RequestMapping("/addform") 
	public String addform() {
		return "addForm";
	}
	
	@RequestMapping("/add") 
	public @ResponseBody String add(@ModelAttribute("usr") UserVO vo , Model model) {
		model.addAttribute("user",vo);
		return dao.insert(vo)?"true":"false";
	}
	
	@RequestMapping("/delete/{num}") //http://localhost:8080/user2/update?num=10&phone=010-5555-5555
	public @ResponseBody String delete(@PathVariable("num") int num) {
		return dao.delete(num)?"true":"false";
	}
	
	@RequestMapping("/update") //http://localhost:8080/user2/update?num=10&phone=010-5555-5555
	public @ResponseBody String update(@ModelAttribute("usr") UserVO vo) {
		return dao.update(vo)?"true":"false";
	}
	

}
