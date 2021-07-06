package com.example.demo.controller.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.mybatis.UserDao;
import com.example.demo.vo.UserVO;

@Controller
public class UserMybatisController {

   @Autowired
   private UserDao dao;
   
   @GetMapping("/mb")
   public @ResponseBody String index() {
      return "index";
   }
   
   @GetMapping("mb/user/add")
   public @ResponseBody int insertUser() 
   {
      return dao.insert(new UserVO(0,"obama","010-6541-6239","obama@usa.com"));
   }
      
   /* 한 행을 추가하고 저장된 자동증가 필드의 값을 가져오는 예*/
   @GetMapping("mb/user/add/getkey")
   public @ResponseBody int insertAndGetKey() {
      UserVO u = new UserVO(0,"trump","010-3910-2540","trump@gmail.com");
      int rows = dao.addAndGetKey(u);
      int generatedKey = u.getNum();
      return generatedKey;
   }

   @GetMapping("mb/user/{num}")
   public @ResponseBody String getUser(@PathVariable int num) 
   {
      return dao.selectById(num).getName();
   }
   
   @GetMapping("mb/user/list")
   public String getUserList(Model model) 
   {
	   
	   model.addAttribute("list", dao.getUserList());
	   
	   
	   return "list";
   }
   
   @GetMapping("mb/user/update/{num}")
   public @ResponseBody int updateUser(@PathVariable("num") int num) {
      return dao.update(new UserVO(num,null,"010-3671-2105","updated@gmail.com"));
   }
   
   @GetMapping("mb/user/delete")
   public @ResponseBody int deleteUser() {
      return dao.delete(6);
   }
}