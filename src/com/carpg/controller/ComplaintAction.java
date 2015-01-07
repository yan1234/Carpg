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
	
	//�ļ��ϴ�����(�����ڶ�ͼ�ϴ���
    private String savePath;// ����·��     
    private List<File> upload; //��Ӧ�ļ����file����װ�ļ�����
    private List<String> uploadContentType; // ��װ�ļ����� 
    private List<String> uploadFileName; // ��װ�ļ��� 
	
	private Complaint complaint = new Complaint();
	private ComplaintDao comDao = new ComplaintImpl();
	private User_CarDao user_carDao = new User_CarImpl();
	//��Ϣ����ķ�����Ϣ
	private String msg;

	//��ʾ��׼���²۵ĵ�����(�²۵�һ��)
	public String complaintStep1() throws Exception{
		//����session�ж��û��Ƿ��½
		String info = (String)request.getSession().getAttribute("user");
		//���sessionΪ�����ʾΪ��½
		if (null == info){
			//�ض��򵽵�½����,�����浱ǰ�Ļ�����ͱ�Թ��һ��)
			request.getSession().setAttribute("step", "step1");
			
			return "login";
		}else{
			//ͨ��session�е��û���Ϣȡ���û��������û����б�
			int userid = Integer.valueOf(info.split("~")[1]);
			List<User_Car> list = user_carDao.getUser_Car(userid);
			String carinfo = "";
			//��ȡ�õ�������Ϣƴ������������ҳ��
			for(int i=0; i < list.size(); i++){
				carinfo += list.get(i).getId() +"," +list.get(i).getCar_brand()+"," +list.get(i).getCar_type();
				carinfo +="~";
			}
			System.out.println("���ص���Ϣ:" +carinfo);
			//��������Ϣ��ӵ�session��
			request.getSession().setAttribute("user_carinfo", carinfo);
			//��ת���²۵ڶ���
			return "step2";
		}		
	}
	//��ʾ��ѡ�����²۳���(�²۵ڶ���)
	public String complaintStep2() throws Exception{
		//��ѡ��ĳ�����Ϣ�ݴ���session��
		String carinfo = request.getParameter("select_cars");
		System.out.println("ѡȡ���͵���Ϣ"+carinfo);
		request.getSession().setAttribute("user_carinfo", carinfo);
		//ҳ����ת����3��
		return "step3";
	}
	//��ʾ����²۵Ĵ������(�²۵�3�������)
	public String complaintStep3() throws Exception{
		//�Ƚ�session�д洢��ѡ�񳵵���Ϣ���û�����Ϣȡ��ȡ��
		String carinfo = (String)request.getSession().getAttribute("user_carinfo");
		String userinfo = (String)request.getSession().getAttribute("user");
		int userid = Integer.valueOf(userinfo.split("~")[1]);
		String username = userinfo.split("~")[2];
		//����Թ��Ϣ��װ��complaint����
		complaint.setUser_id(userid);
		complaint.setUser_name(username);
		complaint.setUser_car_id(Integer.valueOf(carinfo.split(",")[0]));
		complaint.setCar_brand(carinfo.split(",")[1]);
		complaint.setCar_type(carinfo.split(",")[2]);
		//��ӱ�Թ��ʱ��
		Date now = new Date(); 
		//���Է�����޸����ڸ�ʽ
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		complaint.setTime(dateFormat.format(now));
		//ͼƬ�ϴ�����
		complaint.setImage(fileUpload(userid));
		//��ӱ�Թ��Ϣ
		comDao.addComplaint(complaint);
		//����ݴ���session�е��û�������Ϣ
		request.getSession().removeAttribute("user_carinfo");
		return "index";
	}
	//�ļ��ϴ�������,�����û�id����Ϊÿ���û�����һ��Ŀ¼,����ͼƬ����Ե�ַ,�Էֺš�;"�ָ�
	public String fileUpload(int userId) throws Exception{
		String imagePath = "";
		System.out.println("�û���id�� "+userId);
		if (upload != null){
			String path = getSavePath() +"\\" + userId;
			System.out.println("path�� "+path);
			//�����û�������Ŀ¼
			File dir = new File(path);
			if (!dir.exists() && !dir.isDirectory()){
				dir.mkdir();
				System.out.println("dir mk");
			}
			System.out.println("�ļ���С�� "+upload.size()+" "+this.getUploadFileName().size());
			//��ͼƬ��������
			Calendar c = Calendar.getInstance();
			String millis = String.valueOf(c.getTimeInMillis());;
			for (int i=0; i<upload.size(); i++){
				//�ж�ͼƬ���Ƿ�Ϊ��			
				if (null != upload.get(i) && null != this.getUploadFileName().get(i)){
					System.out.println("ͼƬ��������: "+this.getUploadContentType().get(i));
					//��ͼƬ��������
					String[] imageType = this.getUploadContentType().get(i).split("/");
					String tempPath = millis + i + "." + imageType[1];
					//�����ļ������������
					FileOutputStream fos = new FileOutputStream(path+"\\" + tempPath);
					FileInputStream fis=new FileInputStream(this.getUpload().get(i));
					byte[] buffer=new byte[1024];
				    int len=0;
				    while((len=fis.read(buffer))>0){
				        fos.write(buffer, 0, len);
				    }
				    //��ͼƬ·������
				    imagePath += userId + "/" + tempPath +";";
				}			
			}
		}
		return imagePath;
	}
	//��ʾչʾ�²ۣ��²ۻ�����ҳ��
	public String complaintView() throws Exception{
		//ͨ��id�õ���Ҫ���صı�Թ��Ϣ
		System.out.println("��Թ��Ϣid�� "+complaint.getId());
		List<Object> list = comDao.getNewComplaints(complaint.getId());
		System.out.println("��Թ��Ϣ�б� "+list.size());
		//��ȡ�õ���Ϣת��Ϊjsonarray���ݸ�ҳ��
		JsonTool json = new JsonTool();
		msg = json.toJsonArrayString(list);
		System.out.println("json�� "+msg);
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
		//�����·��ת���ɾ���·��  
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
