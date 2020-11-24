package com.zss.controller;

import com.zss.dao.UserDao;
import com.zss.po.Users;
import com.zss.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {

    private String randomStr;
    @Autowired
    private UserDao userDao;

    //发送邮件
    @RequestMapping(value = "sendMail")
    @ResponseBody
    public int sendMail(String username){
        String email = null;
        email =  userDao.selectEmail(username);
        MailUtil mail = new MailUtil();
        if(email == null) return 0;
        else {
            randomStr = mail.sendMail(email,username);
            return 1;
        }
    }

    @RequestMapping("selectAllUsers")
    @ResponseBody
    public List<Users> selectAllUsers(){
        return userDao.selectAllUsers();
    }

    //查询用户是否存在
    @RequestMapping("selectUserByUserName")
    @ResponseBody
    public int selectUserByUserName(String username){
        int effectRow = userDao.selectUserByUserName(username);
        return effectRow;
    }

    //用户注册
    @RequestMapping("userRegist")
    @ResponseBody
    public int userRigist(Users users){
        int effectRow =0;
        int isExist = selectUserByUserName(users.getUsername());
        if(isExist == 1) return 0;
        else {
            effectRow = userDao.insertUser(users);
            return effectRow;
        }
    }

    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public int login(String username,String password){
        int effectRow = userDao.login(username,password);
        return effectRow;
    }

    //修改密码
    @RequestMapping("updatePassword")
    @ResponseBody
    public int updatePassword(Users users){
        int effectRow =0;
        if(users.getTestcode().equals(randomStr)) {
            effectRow = userDao.updatePassword(users);
        }
        return effectRow;
    }

    //修改用户信息
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public int updateUserInfo(Users users){
        int effectRow = userDao.updateUserInfo(users);
        return effectRow;
    }

    //删除用户
    @RequestMapping("deleteUser")
    @ResponseBody
    public int deleteUser(String username){
        return userDao.deleteUser(username);
    }

}
