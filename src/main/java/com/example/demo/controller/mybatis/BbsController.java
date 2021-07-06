package com.example.demo.controller.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BbsSvc;
import com.example.demo.vo.mybatis.BbsVO;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/bbs")
public class BbsController 
{
	@Autowired
	private BbsSvc bbsSvc;
	
	@GetMapping("")
	public String bbs_index() {
		return "BBS working";
	}
	
	@GetMapping("/get/{num}")
	public String getBbsById(@PathVariable("num")int num, Model model) {
		BbsVO vo = bbsSvc.getBbsById(num);
		model.addAttribute("vo", vo);
		return "bbs/get_bbs";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BbsVO> list = bbsSvc.list();
		System.out.println(list.get(0).getTitle());
		model.addAttribute("list", list);
		return "bbs/list";
	}
	
	@GetMapping("/list/page/{n}")
	public String list_page(@PathVariable("n")int pn, Model model) {
		PageInfo<BbsVO> pageinfo = bbsSvc.getUserListPage(pn, 2);
		model.addAttribute("pageinfo", pageinfo);
		//System.out.println(ArrayUtil.toString(pageinfo.getNavigatepageNums()));
		return "bbs/list_page";
	}
}
