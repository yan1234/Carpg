package com.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public Map<String, Integer> control(String type, String param) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Integer> map = null;
		
		if (type.equals(StatisticDao.BRAND_YEAR_COUNT)){ //品牌按年份统计
			sql = "select start_time, count(*),user_car_id from complaint where car_brand=? group by start_time order by start_time DESC";
			
		}else if (type.equals(StatisticDao.BRAND_COUNT_YEAR)){ //某一年内各个品牌的状况统计
			sql = "select car_brand, count(*) from complaint where start_time = ? group by car_brand order by count(*) DESC";
			
		}else if (type.equals(StatisticDao.BRAND_CARTYPE_COUNT)){ //某一个品牌中各个车型的状况统计
			sql = "select car_type, count(*) from complaint where car_brand=? group by car_type order by count(*) DESC";
			
		}else if (type.equals(StatisticDao.PROBLEM_YEAR_COUNT)){ //问题按年份统计
			sql = "select type, count(*) from complaint, car_problems where problem_id = ? group by start_time order by count(*) desc";
			
		}else if (type.equals(StatisticDao.PROBLEM_COUNT_YEAR)){ //某一年内各个问题的状况统计
			sql = "select type, count(*) from complaint, car_problems where start_time = ? group by problem_id order by count(*) desc";
			
		}else if (type.equals(StatisticDao.PROBLEM_CARTYPE_COUNT)){		//某一个问题中各个车型的状况统计
			sql = "select car_brand, count(*) from complaint where problem_id = ? group by car_brand order by count(*) desc";
			
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
				map.put(rs.getString(1), rs.getInt(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
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
