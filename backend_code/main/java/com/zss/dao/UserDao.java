package com.zss.dao;

import com.zss.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserDao {

    //查询所有用户
    List<Users> selectAllUsers();
    //查询用户是否已经存在
    int selectUserByUserName(String username);
    //用户注册
    int insertUser(Users users);
    //登录
    int login(@Param("username") String username,@Param("password") String password);
    //修改密码
    int updatePassword(Users users);
    //修改用户信息
    int updateUserInfo(Users users);
    //查询邮箱信息
    String selectEmail(String username);
    //删除用户
    int deleteUser(String username);
}
