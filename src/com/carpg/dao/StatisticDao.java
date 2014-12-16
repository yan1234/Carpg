package com.carpg.dao;

import java.util.Map;


//��Ϣ����ȡͳ��
public interface StatisticDao {
	
	/*������Ʒ��Ϊͳ�Ʋ��ս��бȽ�*/
	
	//�õ�ĳһ��Ʒ�ư���ݱ��²۵ĵ����ݣ�����ֵΪMap����<��ݣ�����>
	public Map<String, Integer> getCountByYear_brand(String brand);
	
	//�����ͳ�Ƹ���Ʒ�Ƶ�����״����,����ֵΪMap����<Ʒ�ƣ�����>,���ǰ��ս�������
	public Map<String, Integer> getCountByYear(String year);
	
	//ͳ�ư�Ʒ���еĳ���ͳ������״����������ֵΪMap����<���ͣ�����>,�����ս�������
	public Map<String, Integer> getCountByBrand_carType(String brand);
	
	
	/*������״��Ϊͳ�Ʋ��ս��бȽ�*/
	//�õ�һ�����ⰴ��ݱ��²۵�����,����ֵΪMap����<���,����>
	public Map<String, Integer> getCountByYear_problem(String problem);
	
	//�����ͳ�Ƹ��������״����������ֵΪMap����<���⣬����>,������������
	public Map<String, Integer> getCountByProblem_year(String year);
	
	//ͳ��ĳһ�������б��²۵ĸ������͵��������,����ֵΪMap����<���ͣ�����>�� ������������
	public Map<String, Integer> getCountByProblem_carTypes(String problem);

}
