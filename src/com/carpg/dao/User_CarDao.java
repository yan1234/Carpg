package com.carpg.dao;

import java.util.List;

import com.carpg.dto.User_Car;

public interface User_CarDao {
	
	//����û���
	public void addUser_Car(User_Car userCar);
	
	//�����û���,��Щ��Ϣ�ǲ��ܱ����ĵ�
	//�ܸ��ĵ���Ϣ��color, buy_time, mileage, remark
	public void updateUser_Car(User_Car userCar);
	
	//�õ��û���,ͨ���û�����ȡ�û�������Ϣ
	public List<User_Car> getUser_Car(int userid);
	
	//ɾ���û�������Ϣ,ͨ���û�������Ϣ���
	public void delUser_Car(int id);

}
