package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.mybatis.BbsVOsub;

@Mapper
public interface BbsMapper3 
{   
   @Select("WITH RECURSIVE cte AS (\r\n"
   		+ "  SELECT  num, CAST(title AS CHAR(100)) title, writer, hit, wdate, mgr, 1 AS lvl, @rn:=(@rn+1) AS topid\r\n"
   		+ "  FROM (SELECT * FROM bbs ORDER BY wdate DESC)t1, (SELECT @rn:=0)t2\r\n"
   		+ "  WHERE mgr = 0\r\n"
   		+ "  UNION\r\n"
   		+ "  SELECT b.num, CONCAT(REPEAT(' ', p.lvl*4), b.title) title, b.writer, b.hit, b.wdate, b.mgr, p.lvl+1 AS lvl, p.topid AS topid\r\n"
   		+ "  FROM bbs b\r\n"
   		+ "  INNER JOIN cte p ON p.num = b.mgr\r\n"
   		+ ")\r\n"
   		+ "SELECT * FROM cte ORDER BY topid, lvl, wdate DESC;")
   List<BbsVOsub> getBbsList();


}