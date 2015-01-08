package com.carpg.dao;

import java.util.Map;


//��Ϣ����ȡͳ��
public interface StatisticDao {
	
	//������̬�ַ�������ά�����޸�
	public static String BRAND_YEAR_COUNT = "brand_year_count"; //Ʒ�ư����ͳ��
	public static String BRAND_COUNT_YEAR = "brand_count_year"; //ĳһ���ڸ���Ʒ�Ƶ�״��ͳ��
	public static String BRAND_CARTYPE_COUNT = "brand_cartype_count"; //ĳһ��Ʒ���и������͵�״��ͳ��
	public static String PROBLEM_YEAR_COUNT = "problem_year_count"; //���ⰴ���ͳ��
	public static String PROBLEM_COUNT_YEAR = "problem_count_year"; //ĳһ���ڸ��������״��ͳ��
	public static String PROBLEM_CARTYPE_COUNT = "problem_cartype_count"; //ĳһ�������и������͵�״��ͳ��
	
	//�����м�ĵ��Ⱥ���,typeΪִ�еĲ������paramΪ���ݲ���
	public Map<String, Integer> control(String type, String param);
	//����Ҫ��ѯ�����ʹ��ݵĲ������Map<�������>, ������������
	public Map<String, Integer> getCountByParam(String sql, String param);
	
	
	
	
	/*������Ʒ��Ϊͳ�Ʋ��ս��бȽ�*/
	
	//�õ�ĳһ��Ʒ�ư���ݱ��²۵ĵ����ݣ�����ֵΪMap����<��ݣ�����>
	//public Map<String, Integer> getCountByYear_brand(String brand);
	
	//�����ͳ�Ƹ���Ʒ�Ƶ�����״����,����ֵΪMap����<Ʒ�ƣ�����>,���ǰ��ս�������
	//public Map<String, Integer> getCountByYear(String year);
	
	//ͳ�ư�Ʒ���еĳ���ͳ������״����������ֵΪMap����<���ͣ�����>,�����ս�������
	//public Map<String, Integer> getCountByBrand_carType(String brand);
	
	
	/*������״��Ϊͳ�Ʋ��ս��бȽ�*/
	//�õ�һ�����ⰴ��ݱ��²۵�����,����ֵΪMap����<���,����>
	//public Map<String, Integer> getCountByYear_problem(String problem);
	
	//�����ͳ�Ƹ��������״����������ֵΪMap����<���⣬����>,������������
	//public Map<String, Integer> getCountByProblem_year(String year);
	
	//ͳ��ĳһ�������б��²۵ĸ������͵��������,����ֵΪMap����<���ͣ�����>�� ������������
	//public Map<String, Integer> getCountByProblem_carTypes(String problem);
	
	
	

}
