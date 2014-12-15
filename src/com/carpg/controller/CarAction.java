package com.carpg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.carpg.dao.CarDao;
import com.carpg.dao.User_CarDao;
import com.carpg.dto.Car;
import com.carpg.dto.User_Car;
import com.carpg.impl.CarImpl;
import com.carpg.impl.User_CarImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CarAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Car>{
	
	private HttpServletResponse response;  
	private HttpServletRequest request;  
	
	private String msg;
	private Car car = new Car();
	private User_Car user_car = new User_Car();
	private CarDao carDao = new CarImpl();
	private User_CarDao user_carDao = new User_CarImpl();

	//��ʾ�ڱ�Թҳ������µ��û�����
	public String addUserCar() throws Exception{
		System.out.println("����µĳ���"+car.getBrand()+"  "+car.getCar_type());
		System.out.println("����µĳ���"+user_car.getVin()+"  "+user_car.getColor());
		//������������µĳ���
		int carId = carDao.addCar(car);
		//ͨ��session�е��û���Ϣȡ���û��������û����б�
		String info = (String)request.getSession().getAttribute("user");
		int userid = Integer.valueOf(info.split("~")[1]);
		String username = info.split("~")[2];
		
		//�����е���Ϣ��װ��user_car����
		user_car.setUser_id(userid);
		user_car.setUser_name(username);
		user_car.setCar_id(carId);
		user_car.setCar_brand(car.getBrand());
		user_car.setCar_type(car.getCar_type());
		user_car.setVin(request.getParameter("vin"));
		user_car.setColor(request.getParameter("color"));
		user_car.setBuy_time(request.getParameter("buy_time"));
		user_car.setMileage(Integer.valueOf(request.getParameter("mileage")));
		user_car.setRemark(request.getParameter("remark"));
		//���û�����������µ��û���
		user_carDao.addUser_Car(user_car);
		
		//��ҳ�淴�����û���Թѡ��ҳ��	
		List<User_Car> list = user_carDao.getUser_Car(userid);
		msg = "";
		//��ȡ�õ�������Ϣƴ������������ҳ��
		for(int i=0; i < list.size()-1; i++){
			msg += list.get(i).getId() +"," +list.get(i).getCar_brand()+"," +list.get(i).getCar_type();
			msg +="~";
		}
		msg += list.get(list.size()-1).getId() +"," +list.get(list.size()-1).getCar_brand()+"," +list.get(list.size()-1).getCar_type();
		System.out.println("���ص���Ϣ:" +msg);
		//��������Ϣ��ӵ�session��
		request.getSession().setAttribute("user_carinfo", msg);
		return "step2";
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	public Car getModel() {
		// TODO Auto-generated method stub
		return car;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
