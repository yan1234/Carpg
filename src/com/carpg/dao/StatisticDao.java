package com.carpg.dao;

import java.util.List;

//��Ϣ����ȡͳ��
public interface StatisticDao {
	
	//�õ�ĳһ��Ʒ�ư���ݱ��²۵ĵ�����
	public List<Integer> getCountByYear_brand(String brand); 
	

}
