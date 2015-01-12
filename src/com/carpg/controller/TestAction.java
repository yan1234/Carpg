package com.carpg.controller;

import com.carpg.dao.UserinfoDao;
import com.carpg.dto.Report;
import com.carpg.dto.User;
import com.carpg.impl.UserinfoImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TestAction extends ActionSupport implements ModelDriven<Report> {

	private Report report = new Report(); 
	String msg;
	public TestAction() {
		// TODO Auto-generated constructor stub
	}
	
	public String test() throws Exception{
		System.out.println("id: " + report.getId());
		return "";
	}

	public Report getModel() {
		// TODO Auto-generated method stub
		return report;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
