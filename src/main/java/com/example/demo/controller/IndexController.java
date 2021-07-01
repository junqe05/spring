package com.example.demo.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.GuguIO;
import com.example.demo.Service.GuguSvc;
import com.example.demo.user.UserVO;


@Controller
public class IndexController {
	@Autowired //자동으로 객체에 참조가 들어감
	private GuguSvc svc;
	@Autowired
	private GuguIO gio;

   @GetMapping("/")
   public String index() {
	   return "index";
   }
 
   @GetMapping("/gugu") // http://localhost:8080/gugu?dan=6
   public @ResponseBody String gugu(HttpServletRequest request) {
	   return request.getParameter("dan");
   }
   
   @GetMapping("/gugu2")
   public @ResponseBody String gugu2(@RequestParam("dan") int dan) {
	   return dan+"";
   }
   
   @GetMapping("/gugu3") // http://localhost:8080/gugu3?dan=
   public String gugu3(@RequestParam int dan, Model m) {
	   List<String> list = new ArrayList<>();
	   for(int i=1;i<=9;i++) {
		   list.add( String.format("%d * %d = %d",dan, i, dan*i ));
	   }
	   
	   m.addAttribute("dan", dan);
	   m.addAttribute("list", list);
	   
	   return "gugu";
   }
   
   @RequestMapping("/gugu4/dan/{dan}")
   public @ResponseBody String gugu4(@PathVariable("dan") int dan){
	   return dan+"출력";
   }
   
   @GetMapping("/gugu5/view/{dan}") // http://localhost:8080/gugu5/view/5
   public String gugu5(@PathVariable("dan") int dan, ModelMap m){
	   List<String> list = new ArrayList<>();
	   for(int i=1;i<=9;i++) {
		   list.add( String.format("%d * %d = %d",dan, i, dan*i ));
	   }
	   
	   m.addAttribute("dan", dan);
	   //m.addAttribute("list", list);
	   m.put("list", list);
	   return "gugu";
   }
   
   @GetMapping("/gugu6/svc/{dan}") // http://localhost:8080/gugu6/view/5
   public String gugu6(@PathVariable("dan") int dan, ModelMap model){
	   model.put("list",svc.getGugu(dan));
	   return "gugu";
   }
   
   
   @GetMapping("/gugu7/model/{dan}") // http://localhost:8080/gugu7/model/5
   public String gugu7(@PathVariable("dan") int dan, ModelMap model){
	   List<String> list = svc.getGugu(dan);
	   model.put("list",list);
	   //요청한 구구단을 브라우저에 보여줄 뿐만 아니라 파일에도 기록하려고 한다.
	   //GuguIO 클래스의 gugusave(List<String> list)로 저장
	   System.out.println(svc.gugusave(list));
	   return "gugu";
   }
   
   //http://localhost:8080/user/register?num=11&name=kim&phone=010-123-123&email=kim@naver.com
   @RequestMapping(value="/user/register")
   public String test(@ModelAttribute("usr") UserVO vo) { //모델에 usr라는 키로 vo라는 객체를 생성한다.
	   System.out.println(vo.getNum()+vo.getName()+vo.getPhone()+vo.getEmail());
	   return "result";
	   
   }
   
   @GetMapping("/sess")
   public String session_test(HttpServletRequest request) {
	   /* 세션을 구하는 방법
	    * Servlet : request.getSession()
	    * JSP : session.
	    * 
	    * Scope: Page(pageContext), request, session, application
	    * scope.setAttribute("key", value);
	    * scope.getAttrubute("key");
	    */
	   HttpSession session = request.getSession();
	   session.setAttribute("uid", "smith");
	   String uid = (String)session.getAttribute("uid");
	   session.invalidate(); //logout등 세션의 데이터를 전부 삭제
	   session.removeAttribute("uid"); //특정 키의 데이터만 삭제
	   return "result";
   }
   
}