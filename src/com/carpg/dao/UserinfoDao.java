package com.carpg.dao;

import com.carpg.dto.User;
import com.carpg.dto.Userinfo;

public interface UserinfoDao {
	
	//登陆验证,传递参数为用户名和密码,返回error表示登陆失败，返回用户id+name表示登陆成功
	public String checkLogin(String username, String password);
	
	//用户名是否存在检测，主要用于前台的ajax验证
	public boolean checkUser(String username);
	
	//注册，传递参数为用户信息User对象
	public boolean Regist(Userinfo user);
	
	//邮箱验证,传递参数为用户名和匹配的code码
	public boolean verifyUser(String username, String code);
	
	//发送邮件找回密码,参数为绑定邮箱,发送修改密码邮箱验证
	public boolean backPsw(String email);
	
	//找回密码后进行修改,参数为验证匹配的code和新密码
	public boolean updatePsw(String username, String code, String newPsw);
	
	//退出
	public void Logout();

}
