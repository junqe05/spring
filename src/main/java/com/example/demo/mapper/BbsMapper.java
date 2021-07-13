package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.mybatis.BbsVO;


@Mapper
public interface BbsMapper 
{
	@Insert("INSERT INTO bbs VALUES(NULL,#{title},#{writer},#{wdate},#{contents},#{hit},#{pnum})")
	int insertBbs(BbsVO bbsVO);
	
	@Insert("INSERT INTO bbs (title, writerm contents, pnum)VALUES(#{title},#{writer},#{contents},#{pnum})")
	int insertSubBbs(BbsVO bbsVO);

	/* 행을 추가하고 자동증가필드의 값을 파라미터로 전달된 UserVO 의 num 변수에 저장*/
	@Insert("INSERT INTO bbs VALUES(NULL,#{title},#{writer},#{wdate},#{contents},#{hit},#{pnum})")
	@Options(useGeneratedKeys = true, keyProperty = "num")
	int addAndGetKey(BbsVO bbsVO);

	@Select("SELECT * FROM bbs WHERE num = #{num}")
	BbsVO getBbsById( int num);
	
	@Select("SELECT num,title,writer,wdate FROM bbs")
	List<BbsVO> getBbsList();
	
	@Select("WITH RECURSIVE cte AS ("
			+ "  SELECT  num, CAST(title AS CHAR(100)) title, writer, hit, wdate, mgr, 1 AS lvl, @rn:=(@rn+1) AS topid"
			+ "  FROM (SELECT * FROM bbs ORDER BY wdate DESC)t1, (SELECT @rn:=0)t2"
			+ "  WHERE mgr = 0"
			+ "  UNION"
			+ "  SELECT b.num, CONCAT(REPEAT('re:', p.lvl), b.title) title, b.writer, b.hit, b.wdate, b.mgr, p.lvl+1 AS lvl, p.topid AS topid"
			+ "  FROM bbs b"
			+ "  INNER JOIN cte p ON p.num = b.mgr"
			+ ")SELECT * FROM cte ORDER BY topid, lvl, wdate DESC;")
	List<BbsVO> getBbsLvlList();
	

	@Update("UPDATE bbs SET title=#{title}, contents=#{contents} "+
			"WHERE num=#{num}")
	int updateBbs(BbsVO u);
	
	@Delete("DELETE FROM bbs WHERE num=#{num}")
	int deleteBbs( int num);
}