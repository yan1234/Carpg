package com.carpg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.carpg.dao.ComplaintDao;
import com.carpg.dao.User_CarDao;
import com.carpg.dto.Complaint;
import com.carpg.dto.User_Car;
import com.carpg.impl.ComplaintImpl;
import com.carpg.impl.User_CarImpl;
import com.carpg.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ComplaintAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Complaint>{
	
	private HttpServletResponse response;  
	private HttpServletRequest request; 
	
	//文件上传处理(适用于多图上传）
    private String savePath;// 保存路径     
    private List<File> upload; //对应文件域的file，封装文件内容
    private List<String> uploadContentType; // 封装文件类型 
    private List<String> uploadFileName; // 封装文件名 
	
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
			//重定向到登陆界面,并保存当前的活动（发送抱怨第一步)
			request.getSession().setAttribute("step", "step1");
			
			return "login";
		}else{
			//通过session中的用户信息取出用户车给到用户车列表
			int userid = Integer.valueOf(info.split("~")[1]);
			List<User_Car> list = user_carDao.getUser_Car(userid);
			String carinfo = "";
			//将取得的汽车信息拼接起来反馈给页面
			for(int i=0; i < list.size(); i++){
				carinfo += list.get(i).getId() +"," +list.get(i).getCar_brand()+"," +list.get(i).getCar_type();
				carinfo +="~";
			}
			System.out.println("返回的信息:" +carinfo);
			//将车辆信息添加到session中
			request.getSession().setAttribute("user_carinfo", carinfo);
			//跳转到吐槽第二步
			return "step2";
		}		
	}
	//表示是选择了吐槽车型(吐槽第二步)
	public String complaintStep2() throws Exception{
		//将选择的车型信息暂存在session中
		String carinfo = request.getParameter("select_cars");
		System.out.println("选取车型的信息"+carinfo);
		request.getSession().setAttribute("user_carinfo", carinfo);
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
		//添加抱怨的时间
		Date now = new Date(); 
		//可以方便地修改日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		complaint.setTime(dateFormat.format(now));
		//图片上传处理
		complaint.setImage(fileUpload(userid));
		//添加抱怨信息
		comDao.addComplaint(complaint);
		//清除暂存在session中的用户车的信息
		request.getSession().removeAttribute("user_carinfo");
		return "index";
	}
	//文件上传处理函数,传递用户id参数为每个用户创建一个目录,返回图片的相对地址,以分号”;"分割
	public String fileUpload(int userId) throws Exception{
		String imagePath = "";
		System.out.println("用户的id： "+userId);
		if (upload != null){
			String path = getSavePath() +"\\" + userId;
			System.out.println("path： "+path);
			//创建用户单独的目录
			File dir = new File(path);
			if (!dir.exists() && !dir.isDirectory()){
				dir.mkdir();
				System.out.println("dir mk");
			}
			System.out.println("文件大小： "+upload.size()+" "+this.getUploadFileName().size());
			//给图片重新命名
			Calendar c = Calendar.getInstance();
			String millis = String.valueOf(c.getTimeInMillis());;
			for (int i=0; i<upload.size(); i++){
				//判断图片流是否为空			
				if (null != upload.get(i) && null != this.getUploadFileName().get(i)){
					System.out.println("图片名称类型: "+this.getUploadContentType().get(i));
					//给图片重新命名
					String[] imageType = this.getUploadContentType().get(i).split("/");
					String tempPath = millis + i + "." + imageType[1];
					//创建文件的输入输出流
					FileOutputStream fos = new FileOutputStream(path+"\\" + tempPath);
					FileInputStream fis=new FileInputStream(this.getUpload().get(i));
					byte[] buffer=new byte[1024];
				    int len=0;
				    while((len=fis.read(buffer))>0){
				        fos.write(buffer, 0, len);
				    }
				    //将图片路径保存
				    imagePath += userId + "/" + tempPath +";";
				}			
			}
		}
		return imagePath;
	}
	//表示展示吐槽，吐槽互动的页面
	public String complaintView() throws Exception{
		//通过id得到需要加载的抱怨信息
		System.out.println("抱怨信息id： "+complaint.getId());
		List<Object> list = comDao.getNewComplaints(complaint.getId());
		System.out.println("抱怨信息列表： "+list.size());
		//将取得的信息转化为jsonarray传递给页面
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
		System.out.println("json： "+msg);
		return "view";
	}
	
	//展示统计页的吐槽互动，主要是根据carType车型筛选展示
	public String complaintVIewByCarType(String carType) throws Exception{
		List<Object> list = comDao.getNewComplaintsByCarType(carType);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
		return "view";
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
	
	public String getSavePath() {
		//将相对路径转换成绝对路径  
        return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
