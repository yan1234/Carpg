package com.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.carpg.dao.StatisticDao;
import com.carpg.dto.Complaint;
import com.carpg.util.DBHelper;

public class StatisticImpl implements StatisticDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";
	
	//定义标志变量判断是否是执行统计PROBLEM_CARTYPE_COUNT操作
	private int flag = 0;

	public Map<String, Integer> control(String type, String param) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Integer> map = null;
		
		if (type.equals(StatisticDao.BRAND_YEAR_COUNT)){ //品牌按年份统计
			sql = "select start_time, count(*),user_car_id from complaint where car_brand=? group by start_time order by start_time DESC";
			
		}else if (type.equals(StatisticDao.YEAR_BRAND_COUNT)){ //某一年内各个品牌的状况统计
			sql = "select car_brand, count(*) from complaint where start_time = ? group by car_brand order by count(*) DESC";
			
		}else if (type.equals(StatisticDao.BRAND_CARTYPE_COUNT)){ //某一个品牌中各个车型的状况统计
			sql = "select car_type, count(*) from complaint where car_brand=? group by car_type order by count(*) DESC";
			
		}else if (type.equals(StatisticDao.PROBLEM_YEAR_COUNT)){ //问题按年份统计
			sql = "select start_time, count(*) from complaint, car_problems where type = ? group by start_time order by start_time desc";
			
		}else if (type.equals(StatisticDao.YEAR_PROBLEM_COUNT)){ //某一年内各个问题的状况统计
			sql = "select type, count(*) from complaint, car_problems where start_time = ? && complaint.problem_id = car_problems.id group by problem_id order by count(*) desc";
			
		}else if (type.equals(StatisticDao.PROBLEM_CARTYPE_COUNT)){		//某一个问题中各个车型的状况统计
			sql = "select car_type, count(*),car_brand from complaint, car_problems where type = ? group by car_type order by count(*) desc";
			flag = 1;
		}
		map = (LinkedHashMap<String, Integer>)getCountByParam(sql, param);
		return map;
	}
	public Map<String, Integer> getCountByParam(String sql, String param) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param);
			rs = pstmt.executeQuery();
			//得到对应的信息统计列表
			while(rs.next()){
				if (flag == 1){
					//为车型添加品牌信息
					map.put(rs.getString(3)+"."+rs.getString(1), rs.getInt(2));
				}else {
					map.put(rs.getString(1), rs.getInt(2));
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}
	//得到首页最热车型榜
	public Map<String, Integer> getIndexCarType() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		//获取当前的时间年份，展示最近一年统计
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR) -1;
		List<String> car_type = new ArrayList<String>();
		List<String> car_brand = new ArrayList<String>();
		List<String> type = new ArrayList<String>();
		List<Integer> count = new ArrayList<Integer>();
		
		conn = DBHelper.getConn();
		//得到最热问题的排行榜（前3名）
		sql = "select car_brand, car_type, count(*) from complaint where start_time>=? group by car_type order by count(*) desc limit 3";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(year));
			rs = pstmt.executeQuery();
			while(rs.next()){
				//将取得的第一层数据放入到临时变量中
				car_brand.add(rs.getString("car_brand"));
				car_type.add(rs.getString("car_type"));
				count.add(rs.getInt(3));
			}
			rs.close();
			pstmt.close();
			for (int i=0; i < car_type.size(); i++){
				sql = "select type, count(*) from complaint, car_problems where complaint.problem_id = car_problems.id && car_type=? &&  start_time>=? group by problem_id order by count(*) desc limit 1";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				pstmt1.setString(1, car_type.get(i));
				pstmt1.setString(2, String.valueOf(year));
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					type.add(rs1.getString("type"));
				}
				rs1.close();
				pstmt1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close();
		//将取得的数据拼接起来
		for (int i=0; i < car_type.size(); i ++){
			map.put(car_brand.get(i)+"~"+car_type.get(i)+"~"+type.get(i), count.get(i));
		}
		return map;
	}
	//得到首页最热问题榜
	public Map<String, Integer> getIndexProblem() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		//获取当前的时间年份，展示最近一年统计
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR) -1;
		List<Integer> problem_id = new ArrayList<Integer>();
		List<String> type = new ArrayList<String>();
		List<Integer> count = new ArrayList<Integer>();
		List<String> car = new ArrayList<String>();
		conn = DBHelper.getConn();
		//得到最热问题的排行榜（前3名）
		sql = "select problem_id,type,count(*) from complaint,car_problems where start_time >= ? && complaint.problem_id = car_problems.id group by problem_id order by count(*) desc limit 3";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(year));
			rs = pstmt.executeQuery();
			while(rs.next()){
				//将取得的第一层数据放入到临时变量中
				problem_id.add(rs.getInt(1));
				type.add(rs.getString(2));
				count.add(rs.getInt(3));
			}
			rs.close();
			pstmt.close();
			for (int i=0; i < problem_id.size(); i++){
				sql = "select car_brand, car_type, count(*) from complaint where problem_id = ? && start_time>= ? group by car_type order by count(*) desc limit 1";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				pstmt1.setInt(1, problem_id.get(i));
				pstmt1.setString(2, String.valueOf(year));
				ResultSet rs1 = pstmt1.executeQuery();
				while(rs1.next()){
					car.add(rs1.getString("car_brand") + "~" + rs1.getString("car_type"));
				}
				rs1.close();
				pstmt1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close();
		//将取得的数据拼接起来
		for (int i=0; i < problem_id.size(); i ++){
			map.put(type.get(i)+"~"+car.get(i), count.get(i));
		}
		return map;
	}
	//关闭ResultSet和pstmt
	private void close(){
		if (null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接conn
		DBHelper.close();
	}
	/* 之前的版本
	public Map<String, Integer> getCountByYear_brand(String brand) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		sql = "select start_time, count(*),user_car_id from complaint where car_brand=? group by start_time";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			//得到抱怨信息的统计列表
			while(rs.next()){
				map.put(rs.getString("start_time"), rs.getInt(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}

	public Map<String, Integer> getCountByYear(String year) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		sql = "select car_brand, count(*) from complaint where start_time = ? group by car_brand order by count(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			rs = pstmt.executeQuery();
			while(rs.next()){
				map.put(rs.getString("car_brand"), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}

	public Map<String, Integer> getCountByBrand_carType(String brand) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		sql = "select car_type, count(*) from complaint where car_brand=? group by car_type order by count(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			while (rs.next()){
				map.put(rs.getString("car_type"), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}

	public Map<String, Integer> getCountByProblem_carTypes(String problem) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		int problem_id = Integer.valueOf(problem);
		sql = "select car_brand, count(*) from complaint where problem_id = ? group by car_brand desc;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, problem_id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				map.put(rs.getString("car_brand"), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}

	public Map<String, Integer> getCountByProblem_year(String year) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		sql = "select type, count(*) from complaint, car_problems where start_time = ? group by problem_id desc;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			rs = pstmt.executeQuery();
			while (rs.next()){
				map.put(rs.getString("type"), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}

	public Map<String, Integer> getCountByYear_problem(String problem) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		conn = DBHelper.getConn();
		int problem_id = Integer.valueOf(problem);
		sql = "select type, count(*) from complaint, car_problems where problem_id = ? group by start_time desc;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, problem_id);
			rs = pstmt.executeQuery();
			while (rs.next()){
				map.put(rs.getString("type"), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return map;
	}
	
	*/
	
	
}
