package com.example.demo.pds;

import java.sql.PreparedStatement;
import java.util.*;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Repository 
public class UploadDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	String sql;
	
	
	public int upload(String author) {
		UploadVO ul = new UploadVO(author,"NULL","NULL");
		sql = "INSERT into upload (writer, subject, content) VALUES (?,?,?)";	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn->{
							PreparedStatement pstmt;
							pstmt = conn.prepareStatement(sql, new String[] {"num"});
							pstmt.setString(1, ul.getWriter());
							pstmt.setString(2, ul.getSubject());
							pstmt.setString(3, ul.getContent());
							return pstmt;
				}, keyHolder);
		return keyHolder.getKey().intValue();

	}
	
	public boolean upfile(MultipartFile mfiles, int num) {
		UpfileVO uf = new UpfileVO(num, mfiles.getOriginalFilename(),mfiles.getSize());
		sql = "INSERT into upfile (num, filename, filesize) VALUES (?,?,?)";
		int insert = jdbcTemplate.update(sql, uf.getNum(), uf.getFilename(), uf.getFilesize());
		return insert>0?true:false;
	}
	
   public List<UploadVO> list(){
	      String sql = "SELECT u.num, subject, writer, date, filename "+
	               "FROM upload u INNER JOIN upfile f "+
	               "ON u.num=f.num";
	      List<UploadVO> list = jdbcTemplate.query(sql, (rs,i)->{
	         UploadVO uvo = new UploadVO();
	         uvo.setNum(rs.getInt("NUM"));
	         uvo.setSubject(rs.getString("SUBJECT"));
	         uvo.setWriter(rs.getString("WRITER"));
	         uvo.setDate(rs.getDate("DATE"));
	         String fname = rs.getString("FILENAME");
	         if(fname!=null) {
	            UpfileVO fvo = new UpfileVO();
	            fvo.setFilename(fname);
	            UpfileVO[] files = new UpfileVO[1];
	            files[0] = new UpfileVO();
	            files[0].setFilename(fname);
	            uvo.setFiles(files);
	         }
	         return uvo;
	      });
	      return list;
	   }

}
