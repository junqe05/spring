package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BbsMapper;
import com.example.demo.vo.mybatis.BbsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BbsSvc 
{
	@Autowired
	private BbsMapper bbsMapper;
	
	public BbsVO getBbsById(int num) {
		BbsVO vo = bbsMapper.getBbsById(num);
		return vo;
	}
	
	public List<BbsVO> list(){
		List<BbsVO> list = bbsMapper.getBbsLvlList();
		return list;
	}
	
	public PageInfo<BbsVO> getListPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BbsVO> list = bbsMapper.getBbsList();
		PageInfo<BbsVO> pageInfo = new PageInfo<>(list);
        //List<BbsVO> list = pageInfo.getList();  // 지정한 페이지에 속하는 정보만 가져옴
        //PageInfo 클래스에서 Pagination을 위한 다양한 속성과 메소드가 포함되어 있음
		return pageInfo;
	}
	
	public boolean write(BbsVO vo) {
		int n = bbsMapper.insertSubBbs(vo);
		return n>0?true:false;
	}

}
