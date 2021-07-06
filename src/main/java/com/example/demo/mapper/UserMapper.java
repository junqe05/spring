package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.UserVO;

@Mapper
public interface UserMapper 
{
   @Insert("INSERT INTO member VALUES(NULL,#{name},#{phone},#{email}")
   int insertUser(UserVO userVO);

   /* 행을 추가하고 자동증가필드의 값을 파라미터로 전달된 UserVO 의 num 변수에 저장*/
   @Insert("INSERT INTO member VALUES(NULL,#{name},#{phone},#{email})")
   @Options(useGeneratedKeys = true, keyProperty = "num")
   int addAndGetKey(UserVO userVO);

   @Select("SELECT * FROM member WHERE num = #{num}")
   UserVO getUserById( int num);
   
   @Select("SELECT * FROM member")
   List<UserVO> getUserList();

   @Update("UPDATE member SET phone=#{phone}, email=#{email} "+
         "WHERE num=#{num}")
   int updateUser(UserVO u);
   
   @Delete("DELETE FROM member WHERE num=#{num}")
   int deleteUser( int num);
}