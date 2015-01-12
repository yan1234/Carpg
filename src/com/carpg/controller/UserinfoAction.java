package com.carpg.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.carpg.dao.UserinfoDao;
import com.carpg.dao.User_CarDao;
import com.carpg.dto.User;
import com.carpg.dto.User_Car;
import com.carpg.dto.Userinfo;
import com.carpg.impl.UserinfoImpl;
import com.carpg.impl.User_CarImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserinfoAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Userinfo>{


	private HttpServletResponse response;  
	private HttpServletRequest request;  
	private Userinfo user = new Userinfo();
	private UserinfoDao userDao = new UserinfoImpl();
	private String message;
	

	//登陆成功跳转页面，跳转到首页
	public String login() throws Exception{
		//取出用户的活动状态
		String step = (String)request.getSession().getAttribute("step");
		//表示用户上一个活动的状态是在吐槽
		if (null != step){
			//清除session step
			request.getSession().removeAttribute("step");
			//调用抱怨的action中的操作
			ComplaintAction action = new ComplaintAction();
			action.setServletRequest(request);
			return action.complaintStep1();
		}
		else{
			return "index";
		}
		
	}
	//跳转到注册详细页面
	public String loginRe() throws Exception{
		return "regist";
	}
	//注册成功页面,跳转到首页index
	public String regist() throws Exception{
		userDao.Regist(user);
		return "index";
	}
	//提交找回密码申请
	public String returnPsw() throws Exception{
		userDao.backPsw(user.getEmail());
		return "index";
	}
	//找回密码后修改密码请求
	public String updatePsw() throws Exception{
		//从session中取出保存的修改密码的用户信息和识别码,格式为：用户信息+“~”+识别码
		String pswCode = (String)request.getSession().getAttribute("updatePsw");
		if (pswCode != null && pswCode != ""){
			String[] temp = pswCode.split("~");
			userDao.updatePsw(temp[0], temp[1], user.getUserpwd());
		}		
		return "index";
	}
	
	//退出当前登陆
	public String logout() throws Exception{
		//将当前登陆用户的session去掉
		request.getSession().removeAttribute("user");
		return "index";
	}
	public Userinfo getModel() {
		// TODO Auto-generated method stub
		return user;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}



}
