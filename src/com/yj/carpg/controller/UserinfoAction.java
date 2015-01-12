package com.yj.carpg.controller;

import java.util.Calendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;
import com.carpg.dao.UserinfoDao;
import com.carpg.dto.Userinfo;
import com.carpg.impl.UserinfoImpl;

public class UserinfoAction implements ModelDriven<Userinfo>,SessionAware{
	
	private final String SUCC="success";
	private final String LOGIN="login";
	//private final String REGI="register";
	private Userinfo userinfo=new Userinfo();
	private UserinfoDao userinfoDao=new UserinfoImpl();
	private Map<String,Object> session=null;
	
	//和后台同名的方法
	public String checkLogin(){
		/*System.out.println("userinfo-->"+userinfo);
		System.out.println("userid-->"+userinfo.getUserid());
		System.out.println("userpass-->"+userinfo.getUserpwd());*/
		String check = userinfoDao.checkLogin(userinfo.getUsername(), userinfo.getUserpwd());
		if(!"error".equals(check)){
			Calendar c = Calendar.getInstance();
			String info = c.getTimeInMillis()+"~"+check;
			session.put("user", info);
			System.out.println("aaa");
			return SUCC;
		}
		return LOGIN;
	}
	public String logout(){
		session.remove("user");
		return SUCC;
	}
	
	
	public Userinfo getModel() {
		return userinfo;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
