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
		List<BbsVO> list = bbsMapper.getBbsList();
		return list;
	}
	
	public PageInfo<BbsVO> getUserListPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<BbsVO> pageInfo = new PageInfo<>( bbsMapper.getBbsList());
        //List<BbsVO> list = pageInfo.getList();  // 지정한 페이지에 속하는 정보만 가져옴
        //PageInfo 클래스에서 Pagination을 위한 다양한 속성과 메소드가 포함되어 있음
		return pageInfo;
	}

}
