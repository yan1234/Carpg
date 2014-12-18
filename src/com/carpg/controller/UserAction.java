package com.carpg.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.carpg.dao.UserDao;
import com.carpg.dto.User;
import com.carpg.impl.UserImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<User>{

	private final static String ERROR = "error";
	private final static String LOGIN = "login";
	private final static String LOGIN_RE = "login_re";
	private final static String REGIST = "regist";
	private final static String VERIFY = "verify";
	private final static String BACK_PSW = "return_psw";
	private final static String UPDATE_PSW = "set_psw";
	
	private HttpServletResponse response;  
	private HttpServletRequest request;  
	private User user = new User();
	private UserDao userDao = new UserImpl();
	private String message;
	

	//��½�ɹ���תҳ�棬��ת����ҳ
	public String login() throws Exception{
		return "index";
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
		String[] temp = pswCode.split("~");
		userDao.updatePsw(temp[0], temp[1], user.getPassword());
		return "index";
	}
	
	//�˳���ǰ��½
	public String logout() throws Exception{
		//����ǰ��½�û���sessionȥ��
		request.getSession().removeAttribute("user");
		return "index";
	}
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
