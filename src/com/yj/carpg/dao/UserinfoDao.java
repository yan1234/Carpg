package com.yj.carpg.dao;

import com.yj.carpg.dto.Userinfo;

public interface UserinfoDao {
	
	//登录验证
	public boolean checkLogin(Userinfo userinfo);
	
	//注册验证
	public boolean checkRegister(Userinfo userinfo);
	
	//安全退出
	public boolean logout();

}
