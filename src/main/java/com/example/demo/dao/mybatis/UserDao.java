package com.example.demo.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVO;

@Repository
public class UserDao
{
   @Autowired
   private UserMapper userMapper;

   public UserVO selectById(int num) {
      return userMapper.getUserById(num);
    }

   public int insert(UserVO userVO) {
      return userMapper.insertUser(userVO);
   }

   public int addAndGetKey(UserVO userVO) {
      return userMapper.addAndGetKey(userVO);
   }

   public List<UserVO> getUserList() {
      return userMapper.getUserList();
   }

   public int update(UserVO userVO) {
      return userMapper.updateUser(userVO);
   }

   public int delete(int num) {
      return userMapper.deleteUser(num);
   }
}