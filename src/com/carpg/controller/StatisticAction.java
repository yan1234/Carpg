package com.carpg.controller;

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
	
	//������̬�ַ�������ά�����޸�
	public static String BRAND_YEAR_COUNT = "brand_year_count"; //Ʒ�ư����ͳ��
	public static String BRAND_COUNT_YEAR = "brand_count_year"; //ĳһ���ڸ���Ʒ�Ƶ�״��ͳ��
	public static String BRAND_CARTYPE_COUNT = "brand_cartype_count"; //ĳһ��Ʒ���и������͵�״��ͳ��
	public static String PROBLEM_YEAR_COUNT = "problem_year_count"; //���ⰴ���ͳ��
	public static String PROBLEM_COUNT_YEAR = "problem_count_year"; //ĳһ���ڸ��������״��ͳ��
	public static String PROBLEM_CARTYPE_COUNT = "problem_cartype_count"; //ĳһ�������и������͵�״��ͳ��
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	private String type;     //��ʾ����������
	private String param;	//��ʾ���ݵĲ���
	private String msg;		//��ʾ�ش�������
	
	public String getRank() throws Exception{
		//����type�����ִ�в���
		if (type.equals(BRAND_YEAR_COUNT)){
			//ִ�в���
			StatisticDao staDao = new StatisticImpl();
			LinkedHashMap<String, Integer> map = (LinkedHashMap<String, Integer>)staDao.getCountByYear_brand(param);
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
		return "rank";
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
