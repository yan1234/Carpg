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
	private String msgReport;
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
		reAction.getReport_index(1);
		//�õ�����������Ϣ�����ֵ
		msgReport = reAction.getMsg();
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

	public String getMsgReport() {
		return msgReport;
	}

	public void setMsgReport(String msgReport) {
		this.msgReport = msgReport;
	}

	public String getMsgStatistic() {
		return msgStatistic;
	}

	public void setMsgStatistic(String msgStatistic) {
		this.msgStatistic = msgStatistic;
	}


}
