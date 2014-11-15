package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Complaint;

public interface ComplaintDao {
	
	//�ύ��Թ��Ϣ
	public void addComplaint(Complaint complaint);
	
	//���±�Թ��Ϣ,ֻ�в�����Ϣ�������
	//������Ϣ: time, start_time, frequency, course, solution, fee, image, mark
	public void updateComplaint(Complaint complaint);
	
	//ɾ����Թ��Ϣ,����Ϊ��Թ��Ϣid
	public void delComplaint(int id);
	
	//����û�����ı�Թ��Ϣ, ����Ϊ�û���
	public List<Complaint> getComplaints(String username);

}
