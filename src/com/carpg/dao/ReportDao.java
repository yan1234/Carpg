package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Report;

public interface ReportDao {
	
	//����༭��������
	public void addReport(Report report);
	
	//������Ϣչʾ����,������Ϣ�б�, ������Ҫչʾ�����type��ȡ20����Ϣ, 0:�������鱨�棻1:�ٻ���Ϣ
		//idΪ��Ҫ��ȡ�����µ�һ����idֵ�����ڷ�ҳչʾ��-1:Ϊ��ʾ��ȡ��ǰ���µ�ֵ
	public List<Object> getReports(int type, int id);

}
