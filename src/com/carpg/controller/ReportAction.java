package com.carpg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.carpg.dao.ReportDao;
import com.carpg.dto.Report;
import com.carpg.impl.ReportImpl;
import com.carpg.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ModelDriven<Report>{

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//文件上传处理(适用于多图上传）
    private String savePath;// 保存路径 
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private Report report = new Report();
	private ReportDao reportDao = new ReportImpl();
	
	//用于向页面传达至
	private String msg;
	
	//添加报告
	public String addReport() throws Exception{
		
		//设置type的类型
		if (report.getCategory().equals("缺陷调查")){
			report.setType(0);
		}else{report.setType(1);}
		JsonTool json = new JsonTool();
		String temp = json.toJsonString(report);
		System.out.println("report: " + temp);
		//获得图片的路径
		report.setImage(fileUpload(1));
		reportDao.addReport(report);
		
		return "index";
	}
	//展示报告信息列表,根据type类型展示信息(20条）
	public String showReportByType() throws Exception{
		List<Object> list = reportDao.getReportsByType(report.getType(), report.getId(), 20);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);	
		return "show";
	}
	//展示所有的报告信息，不分类别
	public String showReport() throws Exception{
		List<Object> list = null;
		String userinfo = (String)request.getSession().getAttribute("user");
		if (userinfo != null){
			//获取登陆用户信息
			String[] str = userinfo.split("~");
			if (str[2].endsWith("车曝网")){
				list = reportDao.getAll(report.getId(), 20);
			}else {
				list = reportDao.getReports(report.getId(), 20);
			}
		}else {
			list = reportDao.getReports(report.getId(), 20);
		}		
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
		return "show";
	}
	//获取首页报告展示模块的新闻数据（5条）,按type类别分开
	public void getReport_index(int type) throws Exception{
		List<Object> list = reportDao.getReportsByType(type, -1, 5);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
	}
	//审核通过报告信息（超级用户)
	public String passReport() throws Exception{
		String userinfo = (String)request.getSession().getAttribute("user");
		if (userinfo != null){
			//获取登陆用户信息
			String[] str = userinfo.split("~");
			//判断用户是否为车曝网
			if (str[1].endsWith("1")){
				reportDao.passReport(report.getId());
				showReport();
			}			
		}
		return "show";
	}
	//删除报告信息（超级用户）
	public String delReport() throws Exception{
		String userinfo = (String)request.getSession().getAttribute("user");
		if (userinfo != null){
			//获取登陆用户信息
			String[] str = userinfo.split("~");
			if (str[1].endsWith("1")){
				reportDao.delReport(report.getId());
				showReport();
			}
		}
		return "show";
	}
	//文件上传处理函数
	public String fileUpload(int userId) throws Exception{
		String imagePath = "";
		System.out.println("用户的id： "+userId);
		if (file != null){
			String path = getSavePath();
			System.out.println("path： "+path);
			File dir = new File(path);
			if (!dir.exists() && !dir.isDirectory()){
				dir.mkdir();
				System.out.println("dir mk");
			}
			System.out.println("图片名称: "+this.getFileFileName());
			//将当前时间的毫秒数作为文件名
		    Calendar c = Calendar.getInstance();
		    String[] imageType = this.getFileContentType().split("/"); 
		    imagePath = String.valueOf(c.getTimeInMillis()) + "." + imageType[1];
		    System.out.println("imagePath: "+ imagePath);
			//创建文件的输入输出流
			FileOutputStream fos = new FileOutputStream(path+"\\" + imagePath);
			FileInputStream fis=new FileInputStream(this.getFile());
			byte[] buffer=new byte[1024];
		    int len=0;
		    while((len=fis.read(buffer))>0)
		        fos.write(buffer, 0, len);
		    
		    
		}
		imagePath = "report/" + imagePath;
		return imagePath;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getSavePath() {
		 //将相对路径转换成绝对路径  
        return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
