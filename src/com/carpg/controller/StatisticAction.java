package com.carpg.controller;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.carpg.dao.StatisticDao;
import com.carpg.impl.StatisticImpl;
import com.carpg.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;


public class StatisticAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	private String type;     //��ʾ����������
	private String param;	//��ʾ���ݵĲ���
	private String msg;		//��ʾ�ش�������
	
	//��ȡheader�������й̶������а�
	public String rank() throws Exception{
		return "rank";
	}
	//���ݲ�������ȡ���а�
	public String getRank() throws Exception{
		System.out.println("��Ʒ��: "+param);
		param = URLDecoder.decode(URLDecoder.decode(param, "utf-8"));
		System.out.println("��Ʒ��: "+param);
		//ִ�в���
		StatisticDao staDao = new StatisticImpl();
		//�����м�ĵ��Ȳ�control����
		LinkedHashMap<String, Integer> map = (LinkedHashMap<String, Integer>)staDao.control(type, param);
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		JSONArray array = new JSONArray();
		//����hashMap�����뵽����Դ(JSONArray)
		while (iterator.hasNext()){
			 Map.Entry element = (Map.Entry)iterator.next(); 
	         String  key = (String)element.getKey(); 
	         int  value = (Integer)element.getValue();
	         JSONObject obj = new JSONObject();
	         obj.put("name", key);
	         obj.put("nub", value);
	         array.add(obj);
		}
		msg = array.toString();
		return "rank";
	}
	//����ͳ�������ת����Ӧ���²۱�Թ,���ݵĲ���ΪcarType(param)
	public String toComplaint() throws Exception{
		String temp;
		ComplaintAction cAction = new ComplaintAction();
		//��carType���ݸ���Թ��������ɸѡ��Ӧ�ı�Թ��Ϣ
		cAction.setServletRequest(request);
		System.out.println("���ͣ� "+param);
		//���������ʹ��ݸ���Թ����action
		temp = cAction.complaintVIewByCarType(param);
		msg = cAction.getMsg();
		System.out.println("��Թ�б� "+msg);
		return temp;
	}
	//ͳ����ҳ���������������ȳ��Ͱ�,flag=0:�������⣬1�����ȳ���
	public void index(int flag) throws Exception{
		StatisticDao staDao = new StatisticImpl();
		LinkedHashMap<String, Integer> map = null;
		if (flag == 0){
			map = (LinkedHashMap<String, Integer>)staDao.getIndexProblem();
		}else if (flag == 1){
			map = (LinkedHashMap<String, Integer>)staDao.getIndexCarType();
		}
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		JSONArray array = new JSONArray();
		//����hashMap�����뵽����Դ(JSONArray)
		while (iterator.hasNext()){
			 Map.Entry element = (Map.Entry)iterator.next(); 
	         String  key = (String)element.getKey(); 
	         int  value = (Integer)element.getValue();
	         JSONObject obj = new JSONObject();
	         obj.put("name", key);
	         obj.put("nub", value);
	         array.add(obj);
		}
		msg = array.toString();
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

}
