package com.carpg.dao;

import java.util.Map;


//��Ϣ����ȡͳ��
public interface StatisticDao {
	
	//�õ�ĳһ��Ʒ�ư���ݱ��²۵ĵ����ݣ�����ֵΪMap����<��ݣ�����>
	public Map<String, Integer> getCountByYear_brand(String brand);
	
	//�����ͳ�Ƹ���Ʒ�Ƶ�����״����,����ֵΪMap����<Ʒ�ƣ�����>,���ǰ��ս�������
	public Map<String, Integer> getCountByYear(String year);
	

}
