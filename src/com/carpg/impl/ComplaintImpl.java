package com.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carpg.dao.ComplaintDao;
import com.carpg.dto.Complaint;
import com.carpg.util.DBHelper;

public class ComplaintImpl implements ComplaintDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";

	public void addComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		//临时调整吐槽问题发生的时间为只截取年份
		String[] start_time = complaint.getStart_time().split("-");
		complaint.setStart_time(start_time[0]);
		
		conn = DBHelper.getConn();
		sql = "insert into complaint value(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, complaint.getUser_id());
			pstmt.setString(2, complaint.getUser_name());
			pstmt.setInt(3, complaint.getUser_car_id());
			pstmt.setString(4, complaint.getCar_brand());
			pstmt.setString(5, complaint.getCar_type());
			pstmt.setInt(6, complaint.getProblem_id());
			pstmt.setString(7, complaint.getTime());
			pstmt.setString(8, complaint.getStart_time());
			pstmt.setString(9, complaint.getFrequency());
			pstmt.setString(10, complaint.getCourse());
			pstmt.setString(11, complaint.getSolution());
			pstmt.setInt(12, complaint.getFee());
			pstmt.setString(13, complaint.getImage());
			pstmt.setString(14, complaint.getMark());
			pstmt.setInt(15, complaint.getMileage());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();

	}

	public void delComplaint(int id) {
		// TODO Auto-generated method stub
		conn = DBHelper.getConn();
		sql = "delete from complaint where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
	}

	public List<Complaint> getComplaints(String username) {
		// TODO Auto-generated method stub
		List<Complaint> list = new ArrayList<Complaint>();
		conn = DBHelper.getConn();
		sql = "select * from compliant where user_name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Complaint com = new Complaint();
				com.setId(rs.getInt("id"));
				com.setUser_id(rs.getInt("user_id"));
				com.setUser_name(rs.getString("user_name"));
				com.setUser_car_id(rs.getInt("user_car_id"));
				com.setCar_brand(rs.getString("car_brand"));
				com.setCar_type(rs.getString("car_type"));
				com.setProblem_id(rs.getInt("problem_id"));
				com.setTime(rs.getString("time"));
				com.setStart_time(rs.getString("start_time"));
				com.setFrequency(rs.getString("frequency"));
				com.setCourse(rs.getString("course"));
				com.setSolution(rs.getString("solution"));
				com.setFee(rs.getInt("fee"));
				com.setImage(rs.getString("image"));
				com.setMark(rs.getString("mark"));
				com.setMileage(rs.getInt("mileage"));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return list;
	}

	public void updateComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		conn = DBHelper.getConn();
		sql = "update complaint set time=?, start_time=?, frequency=?, course=?,"+
						"solution=?, fee=?, image=?, mark=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, complaint.getTime());
			pstmt.setString(2, complaint.getStart_time());
			pstmt.setString(3, complaint.getFrequency());
			pstmt.setString(4, complaint.getCourse());
			pstmt.setString(5, complaint.getSolution());
			pstmt.setInt(6, complaint.getFee());
			pstmt.setString(7, complaint.getImage());
			pstmt.setString(8, complaint.getMark());
			pstmt.setInt(9, complaint.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();

	}

	public List<Object> getNewComplaints(int id) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		conn = DBHelper.getConn();
		try {
			//从最新的开始取出数据
			if (id == -1){
				sql = "select * from complaint, car_problems order by time desc limit 20";
				pstmt = conn.prepareStatement(sql);
				System.out.println("执行查询语句：");
			}else {
				sql = "select * from complaint, car_problems where id<=? order by time desc limit 20";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()){
				System.out.println("取出数据： "+rs.getRow());
				Complaint com = new Complaint();
				com.setId(rs.getInt("id"));
				com.setUser_id(rs.getInt("user_id"));
				com.setUser_name(rs.getString("user_name"));
				com.setUser_car_id(rs.getInt("user_car_id"));
				com.setCar_brand(rs.getString("car_brand"));
				com.setCar_type(rs.getString("car_type"));
				com.setProblem_id(rs.getInt("problem_id"));
				com.setTime(rs.getString("time"));
				com.setStart_time(rs.getString("start_time"));
				com.setFrequency(rs.getString("frequency"));
				com.setCourse(rs.getString("course"));
				com.setSolution(rs.getString("solution"));
				com.setFee(rs.getInt("fee"));
				com.setImage(rs.getString("image"));
				com.setMark(rs.getString("mark"));
				com.setMileage(rs.getInt("mileage"));
				
				com.setProblem_type(rs.getString("type"));
				com.setProblem_problem(rs.getString("problem"));
				com.setProblem_detail(rs.getString("detail"));
				list.add(com);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		System.out.println("信息列表："+ list.size());
		return list;
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

	public List<Object> getNewComplaintsByCarType(String carType) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		conn = DBHelper.getConn();
		try {
			//从最新的开始取出数据
			sql = "select * from complaint, car_problems where car_type = ? order by time desc limit 20";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carType);
			System.out.println("执行查询语句：");
			rs = pstmt.executeQuery();
			while (rs.next()){
				System.out.println("取出数据： "+rs.getRow());
				Complaint com = new Complaint();
				com.setId(rs.getInt("id"));
				com.setUser_id(rs.getInt("user_id"));
				com.setUser_name(rs.getString("user_name"));
				com.setUser_car_id(rs.getInt("user_car_id"));
				com.setCar_brand(rs.getString("car_brand"));
				com.setCar_type(rs.getString("car_type"));
				com.setProblem_id(rs.getInt("problem_id"));
				com.setTime(rs.getString("time"));
				com.setStart_time(rs.getString("start_time"));
				com.setFrequency(rs.getString("frequency"));
				com.setCourse(rs.getString("course"));
				com.setSolution(rs.getString("solution"));
				com.setFee(rs.getInt("fee"));
				com.setImage(rs.getString("image"));
				com.setMark(rs.getString("mark"));
				com.setMileage(rs.getInt("mileage"));
				
				com.setProblem_type(rs.getString("type"));
				com.setProblem_problem(rs.getString("problem"));
				com.setProblem_detail(rs.getString("detail"));
				list.add(com);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		System.out.println("信息列表："+ list.size());
		return list;
	}
}

