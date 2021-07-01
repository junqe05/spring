package com.example.demo.pds;

import java.sql.PreparedStatement;

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
public class PDSDAO {
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

}
