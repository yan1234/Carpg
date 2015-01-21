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
	
	
	
	private String type;     //表示操作的类型
	private String param;	//表示传递的参数
	private String msg;		//表示回传的数据
	
	//获取header导航栏中固定的排行榜
	public String rank() throws Exception{
		return "rank";
	}
	//根据参数类别获取排行榜
	public String getRank() throws Exception{
		System.out.println("车品牌: "+param);
		param = URLDecoder.decode(URLDecoder.decode(param, "utf-8"));
		System.out.println("车品牌: "+param);
		//执行操作
		StatisticDao staDao = new StatisticImpl();
		//调用中间的调度层control函数
		LinkedHashMap<String, Integer> map = (LinkedHashMap<String, Integer>)staDao.control(type, param);
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		JSONArray array = new JSONArray();
		//遍历hashMap并载入到数据源(JSONArray)
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
	//问题统计最后跳转到对应的吐槽抱怨,传递的参数为carType(param)
	public String toComplaint() throws Exception{
		String temp;
		ComplaintAction cAction = new ComplaintAction();
		//将carType传递给抱怨操作用于筛选对应的抱怨信息
		cAction.setServletRequest(request);
		System.out.println("车型： "+param);
		//将参数车型传递给抱怨操作action
		temp = cAction.complaintVIewByCarType(param);
		msg = cAction.getMsg();
		System.out.println("抱怨列表： "+msg);
		return temp;
	}
	//统计首页的最热问题榜和最热车型榜,flag=0:最热问题，1：最热车型
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
		//遍历hashMap并载入到数据源(JSONArray)
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
