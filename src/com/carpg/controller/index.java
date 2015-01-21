package com.carpg.controller;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class index extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//请求的类别
	private int typeReport;
	
	//服务器向前端传递的值
	//信息报告的值
	private String msgReport0;
	private String msgReport1;
	//统计数据的值
	private String msgStatistic0;
	private String msgStatistic1;
	
	//首页的请求转发
	public String execute() throws Exception{
		System.out.println("首页请求");
		//取出数据库的链接属性，并存在全局变量application中
		ResourceBundle resources = ResourceBundle.getBundle("jdbc");
		ServletContext application = ServletActionContext.getServletContext();
		application.setAttribute("driver", resources.getString("driver").trim());
		application.setAttribute("url", resources.getString("url").trim());
		application.setAttribute("user", resources.getString("user").trim());
		application.setAttribute("password", resources.getString("password").trim());
		
		//取出数据
		getData();
		
		return "index";
	}
	//取出首页默认的数据
	private void getData() throws Exception{
		//将首页动态展示的信息传递给页面前端（session或其他）
		//得到首页的统计展示数据
		StatisticAction staAction = new StatisticAction();
		staAction.index(0);
		msgStatistic0 = staAction.getMsg();
		staAction.index(1);
		msgStatistic1 = staAction.getMsg();	
		//得到首页的调查报告，汽车召回的数据
		ReportAction reAction = new ReportAction();
		//得到后端请求的信息报告的值包括调查报告和信息召回
		reAction.getReport_index(0);
		msgReport0 = reAction.getMsg();
		reAction.getReport_index(1);
		msgReport1 = reAction.getMsg();
		
		//将数据存储在session中
		request.getSession().setAttribute("msgReport0", msgReport0);
		request.getSession().setAttribute("msgReport1", msgReport1);
		
		request.getSession().setAttribute("msgStatistic0", msgStatistic0);
		request.getSession().setAttribute("msgStatistic1", msgStatistic1);
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
		
	}

	public int getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(int typeReport) {
		this.typeReport = typeReport;
	}

	public String getMsgReport0() {
		return msgReport0;
	}

	public void setMsgReport0(String msgReport0) {
		this.msgReport0 = msgReport0;
	}

	public String getMsgReport1() {
		return msgReport1;
	}

	public void setMsgReport1(String msgReport1) {
		this.msgReport1 = msgReport1;
	}

	public String getMsgStatistic0() {
		return msgStatistic0;
	}

	public void setMsgStatistic0(String msgStatistic0) {
		this.msgStatistic0 = msgStatistic0;
	}

	public String getMsgStatistic1() {
		return msgStatistic1;
	}

	public void setMsgStatistic1(String msgStatistic1) {
		this.msgStatistic1 = msgStatistic1;
	}


}
