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
	

	//��½�ɹ���תҳ�棬��ת����ҳ
	public String login() throws Exception{
		//ȡ���û��Ļ״̬
		String step = (String)request.getSession().getAttribute("step");
		//��ʾ�û���һ�����״̬�����²�
		if (null != step){
			//���session step
			request.getSession().removeAttribute("step");
			//���ñ�Թ��action�еĲ���
			ComplaintAction action = new ComplaintAction();
			action.setServletRequest(request);
			return action.complaintStep1();
		}
		else{
			return "index";
		}
		
	}
	//��ת��ע����ϸҳ��
	public String loginRe() throws Exception{
		return "regist";
	}
	//ע��ɹ�ҳ��,��ת����ҳindex
	public String regist() throws Exception{
		userDao.Regist(user);
		return "index";
	}
	//�ύ�һ���������
	public String returnPsw() throws Exception{
		userDao.backPsw(user.getEmail());
		return "index";
	}
	//�һ�������޸���������
	public String updatePsw() throws Exception{
		//��session��ȡ��������޸�������û���Ϣ��ʶ����,��ʽΪ���û���Ϣ+��~��+ʶ����
		String pswCode = (String)request.getSession().getAttribute("updatePsw");
		if (pswCode != null && pswCode != ""){
			String[] temp = pswCode.split("~");
			userDao.updatePsw(temp[0], temp[1], user.getUserpwd());
		}		
		return "index";
	}
	
	//�˳���ǰ��½
	public String logout() throws Exception{
		//����ǰ��½�û���sessionȥ��
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
