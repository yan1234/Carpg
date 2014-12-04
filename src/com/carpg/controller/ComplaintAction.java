package com.carpg.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.carpg.dao.ComplaintDao;
import com.carpg.dto.Complaint;
import com.carpg.impl.ComplaintImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ComplaintAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Complaint>{

	private static String COMPLAINT = "complaint";
	
	private HttpServletResponse response;  
	private HttpServletRequest request; 
	
	private Complaint complaint = new Complaint();
	private ComplaintDao comDao = new ComplaintImpl();
	private String types;


	public String execute() throws Exception{
		//��ʾ��׼���²۵ĵ�����
		if (types.equals(COMPLAINT)){
			//����session�ж��û��Ƿ��½
			String info = (String)request.getSession().getAttribute("sessioninfo");
			//���sessionΪ�����ʾΪ��½
			if (null == info){
				//�ض��򵽵�½����
			}else{
				//��ת���²۵ڶ���
			}
		}
		return "test";
		
	}
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	public Complaint getModel() {
		// TODO Auto-generated method stub
		return complaint;
	}

	public String getTypes() {
		return types;
	}


	public void setTypes(String types) {
		this.types = types;
	}
}
