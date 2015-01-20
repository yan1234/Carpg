package com.carpg.dao;

import java.util.Map;


//信息的提取统计
public interface StatisticDao {
	
	//创建静态字符串便于维护和修改
	public static String BRAND_YEAR_COUNT = "brand_year_count"; //品牌按年份统计
	public static String YEAR_BRAND_COUNT = "year_brand_count"; //某一年内各个品牌的状况统计 year_brand_count
	public static String BRAND_CARTYPE_COUNT = "brand_cartype_count"; //某一个品牌中各个车型的状况统计
	public static String PROBLEM_YEAR_COUNT = "problem_year_count"; //问题按年份统计
	public static String YEAR_PROBLEM_COUNT = "year_problem_count"; //某一年内各个问题的状况统计
	public static String PROBLEM_CARTYPE_COUNT = "problem_cartype_count"; //某一个问题中各个车型的状况统计
	
	//定义中间的调度函数,type为执行的操作类别，param为传递参数
	public Map<String, Integer> control(String type, String param);
	//根据要查询的语句和传递的参数获得Map<类别，数量>, 并按降序排列
	public Map<String, Integer> getCountByParam(String sql, String param);
	
	
	
	
	/*以汽车品牌为统计参照进行比较*/
	
	//得到某一个品牌按年份被吐槽的的数据，返回值为Map类型<年份，数量>
	//public Map<String, Integer> getCountByYear_brand(String brand);
	
	//按年份统计各个品牌的问题状况量,返回值为Map类型<品牌，数量>,并是按照降序排列
	//public Map<String, Integer> getCountByYear(String year);
	
	//统计按品牌中的车型统计问题状况量，返回值为Map类型<车型，数量>,并按照降序排列
	//public Map<String, Integer> getCountByBrand_carType(String brand);
	
	
	/*以问题状况为统计参照进行比较*/
	//得到一个问题按年份被吐槽的数据,返回值为Map类型<年份,数量>
	//public Map<String, Integer> getCountByYear_problem(String problem);
	
	//按年份统计各个问题的状况量，返回值为Map类型<问题，数量>,并按降序排列
	//public Map<String, Integer> getCountByProblem_year(String year);
	
	//统计某一个问题中被吐槽的各个车型的排行情况,返回值为Map类型<车型，数量>， 并按降序排列
	//public Map<String, Integer> getCountByProblem_carTypes(String problem);
	
	
	

}
