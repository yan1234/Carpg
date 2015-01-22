package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Report;

public interface ReportDao {
	
	//����༭��������
	public void addReport(Report report);
	
	//������Ϣչʾ����,������Ϣ�б�, ������Ҫչʾ�����type��ȡsize����Ϣ, 0:�������鱨�棻1:�ٻ���Ϣ
		//idΪ��Ҫ��ȡ�����µ�һ����idֵ�����ڷ�ҳչʾ��-1:Ϊ��ʾ��ȡ��ǰ���µ�ֵ
	public List<Object> getReportsByType(int type, int id, int size);
	
	//������Ϣչʾ����,������Ϣ�б�(size��)
	//idΪ��Ҫ��ȡ�����µ�һ����idֵ�����ڷ�ҳչʾ��-1:Ϊ��ʾ��ȡ��ǰ���µ�ֵ
	public List<Object> getReports(int id, int size);
	
	//����Ա��ȡ���еı���������
	public List<Object> getAll(int id, int size);
	
	//���ͨ��������Ϣ
	public void passReport(int id);
	
	//ɾ��������Ϣ
	public void delReport(int id);
	

}
