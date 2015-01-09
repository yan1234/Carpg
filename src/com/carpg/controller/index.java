package com.carpg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private String msgStatistic;
	
	//��ҳ������ת��
	public String execute() throws Exception{
		System.out.println("��ҳ����");
		//����ҳ��̬չʾ����Ϣ���ݸ�ҳ��ǰ�ˣ�session��������
		//�õ���ҳ��ͳ��չʾ����
		StatisticAction staAction = new StatisticAction();
		//�õ���ҳ�ĵ��鱨�棬�����ٻص�����
		ReportAction reAction = new ReportAction();
		//�õ�����������Ϣ�����ֵ�������鱨�����Ϣ�ٻ�
		reAction.getReport_index(0);
		msgReport0 = reAction.getMsg();
		reAction.getReport_index(1);
		msgReport1 = reAction.getMsg();
		
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

	public int getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(int typeReport) {
		this.typeReport = typeReport;
	}

	public String getMsgStatistic() {
		return msgStatistic;
	}

	public void setMsgStatistic(String msgStatistic) {
		this.msgStatistic = msgStatistic;
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


}
