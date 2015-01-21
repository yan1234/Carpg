package com.carpg.controller;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class index extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//��������
	private int typeReport;
	
	//��������ǰ�˴��ݵ�ֵ
	//��Ϣ�����ֵ
	private String msgReport0;
	private String msgReport1;
	//ͳ�����ݵ�ֵ
	private String msgStatistic0;
	private String msgStatistic1;
	
	//��ҳ������ת��
	public String execute() throws Exception{
		System.out.println("��ҳ����");
		//ȡ�����ݿ���������ԣ�������ȫ�ֱ���application��
		ResourceBundle resources = ResourceBundle.getBundle("jdbc");
		ServletContext application = ServletActionContext.getServletContext();
		application.setAttribute("driver", resources.getString("driver").trim());
		application.setAttribute("url", resources.getString("url").trim());
		application.setAttribute("user", resources.getString("user").trim());
		application.setAttribute("password", resources.getString("password").trim());
		
		//ȡ������
		getData();
		
		return "index";
	}
	//ȡ����ҳĬ�ϵ�����
	private void getData() throws Exception{
		//����ҳ��̬չʾ����Ϣ���ݸ�ҳ��ǰ�ˣ�session��������
		//�õ���ҳ��ͳ��չʾ����
		StatisticAction staAction = new StatisticAction();
		staAction.index(0);
		msgStatistic0 = staAction.getMsg();
		staAction.index(1);
		msgStatistic1 = staAction.getMsg();	
		//�õ���ҳ�ĵ��鱨�棬�����ٻص�����
		ReportAction reAction = new ReportAction();
		//�õ�����������Ϣ�����ֵ�������鱨�����Ϣ�ٻ�
		reAction.getReport_index(0);
		msgReport0 = reAction.getMsg();
		reAction.getReport_index(1);
		msgReport1 = reAction.getMsg();
		
		//�����ݴ洢��session��
		request.getSession().setAttribute("msgReport0", msgReport0);
		request.getSession().setAttribute("msgReport1", msgReport1);
		
		request.getSession().setAttribute("msgStatistic0", msgStatistic0);
		request.getSession().setAttribute("msgStatistic1", msgStatistic1);
	}
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
		
	}

	public int getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(int typeReport) {
		this.typeReport = typeReport;
	}

	public String getMsgReport0() {
		return msgReport0;
	}

	public void setMsgReport0(String msgReport0) {
		this.msgReport0 = msgReport0;
	}

	public String getMsgReport1() {
		return msgReport1;
	}

	public void setMsgReport1(String msgReport1) {
		this.msgReport1 = msgReport1;
	}

	public String getMsgStatistic0() {
		return msgStatistic0;
	}

	public void setMsgStatistic0(String msgStatistic0) {
		this.msgStatistic0 = msgStatistic0;
	}

	public String getMsgStatistic1() {
		return msgStatistic1;
	}

	public void setMsgStatistic1(String msgStatistic1) {
		this.msgStatistic1 = msgStatistic1;
	}


}
