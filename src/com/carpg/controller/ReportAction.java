package com.carpg.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
	
	//�ļ��ϴ�����(�����ڶ�ͼ�ϴ���
    private String savePath;// ����·�� 
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private Report report = new Report();
	private ReportDao reportDao = new ReportImpl();
	
	//������ҳ�洫����
	private String msg;
	
	//��ӱ���
	public String addReport() throws Exception{
		//����type������
		if (report.getCategory().equals("ȱ�ݵ���")){
			report.setType(0);
		}else{report.setType(1);}
		JsonTool json = new JsonTool();
		String temp = json.toJsonString(report);
		System.out.println("report: " + temp);
		//���ͼƬ��·��
		report.setImage(fileUpload(1));
		reportDao.addReport(report);
		return "index";
	}
	//չʾ������Ϣ�б�,����type����չʾ��Ϣ(20����
	public String showReportByType() throws Exception{
		List<Object> list = reportDao.getReportsByType(report.getType(), report.getId(), 20);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);	
		return "";
	}
	//չʾ���еı�����Ϣ���������
	public String showReport() throws Exception{
		List<Object> list = reportDao.getReports(report.getId(), 20);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
		return "show";
	}
	//��ȡ��ҳ����չʾģ����������ݣ�5����,��type���ֿ�
	public void getReport_index(int type) throws Exception{
		List<Object> list = reportDao.getReportsByType(type, -1, 5);
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
	}
	//�ļ��ϴ�������
	public String fileUpload(int userId) throws Exception{
		String imagePath = "";
		System.out.println("�û���id�� "+userId);
		if (file != null){
			String path = getSavePath();
			System.out.println("path�� "+path);
			File dir = new File(path);
			if (!dir.exists() && !dir.isDirectory()){
				dir.mkdir();
				System.out.println("dir mk");
			}
			System.out.println("ͼƬ����: "+this.getFileFileName());
			//����ǰʱ��ĺ�������Ϊ�ļ���
		    Calendar c = Calendar.getInstance();
		    String[] imageType = this.getFileContentType().split("/"); 
		    imagePath = String.valueOf(c.getTimeInMillis()) + "." + imageType[1];
		    System.out.println("imagePath: "+ imagePath);
			//�����ļ������������
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
		 //�����·��ת���ɾ���·��  
        return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
