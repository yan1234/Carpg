package com.carpg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.carpg.dao.ComplaintDao;
import com.carpg.dao.User_CarDao;
import com.carpg.dto.Complaint;
import com.carpg.dto.User_Car;
import com.carpg.impl.ComplaintImpl;
import com.carpg.impl.User_CarImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ComplaintAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Complaint>{
	
	private HttpServletResponse response;  
	private HttpServletRequest request; 
	
	private Complaint complaint = new Complaint();
	private ComplaintDao comDao = new ComplaintImpl();
	private User_CarDao user_carDao = new User_CarImpl();
	//消息处理的返回信息
	private String msg;

	//表示是准备吐槽的的请求(吐槽第一步)
	public String complaintStep1() throws Exception{
		//根据session判断用户是否登陆
		String info = (String)request.getSession().getAttribute("user");
		//如果session为空则表示为登陆
		if (null == info){
			//重定向到登陆界面
			return "login";
		}else{
			//通过session中的用户信息取出用户车给到用户车列表
			int userid = Integer.valueOf(info.split("~")[1]);
			List<User_Car> list = user_carDao.getUser_Car(userid);
			msg = "";
			//将取得的汽车信息拼接起来反馈给页面
			for(int i=0; i < list.size()-1; i++){
				msg += list.get(i).getId() +"," +list.get(i).getCar_brand()+"," +list.get(i).getCar_type();
				msg +="~";
			}
			msg += list.get(list.size()-1).getId() +"," +list.get(list.size()-1).getCar_brand()+"," +list.get(list.size()-1).getCar_type();
			System.out.println("返回的信息:" +msg);
			//将车辆信息添加到session中
			request.getSession().setAttribute("user_carinfo", msg);
			//跳转到吐槽第二步
			return "step2";
		}		
	}
	//表示是选择了吐槽车型(吐槽第二步)
	public String complaintStep2() throws Exception{
		//将选择的车型信息暂存在session中
		msg = request.getParameter("select_cars");
		System.out.println("选取车型的信息"+msg);
		request.getSession().setAttribute("user_carinfo", msg);
		//页面跳转到第3步
		return "step3";
	}
	//表示完成吐槽的处理操作(吐槽第3步，完成)
	public String complaintStep3() throws Exception{
		//先将session中存储的选择车的信息和用户的信息取出取出
		String carinfo = (String)request.getSession().getAttribute("user_carinfo");
		String userinfo = (String)request.getSession().getAttribute("user");
		int userid = Integer.valueOf(userinfo.split("~")[1]);
		String username = userinfo.split("~")[2];
		//将抱怨信息封装到complaint类中
		complaint.setUser_id(userid);
		complaint.setUser_name(username);
		complaint.setUser_car_id(Integer.valueOf(carinfo.split(",")[0]));
		complaint.setCar_brand(carinfo.split(",")[1]);
		complaint.setCar_type(carinfo.split(",")[2]);
		//添加抱怨信息
		comDao.addComplaint(complaint);
		return "index";
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

	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
}
