package com.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carpg.dao.ReportDao;
import com.carpg.dto.Report;
import com.carpg.util.DBHelper;

public class ReportImpl implements ReportDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";

	public void addReport(Report report) {
		// TODO Auto-generated method stub
		conn = DBHelper.getConn();
		sql = "insert into report value(null, ?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report.getType());
			pstmt.setString(2, report.getCategory());
			pstmt.setString(3, report.getTitle());
			pstmt.setString(4, report.getSource());
			pstmt.setString(5, report.getAuthor());
			pstmt.setString(6, report.getTime());
			pstmt.setString(7, report.getContent());
			pstmt.setString(8, report.getImage());
			pstmt.setString(9, report.getUrl());
			pstmt.setString(10, report.getCar_type());
			pstmt.setString(11, report.getAgency());
			pstmt.setInt(12, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
	}

	public List<Object> getReportsByType(int type, int id, int size) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		conn = DBHelper.getConn();
		try {
			if (id == -1){
				sql = "select * from report where type = ? && ok = 1 order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type);
				pstmt.setInt(2, size);
			}else{
				sql = "select * from report where type=? && id<=? && ok = 1 order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type);
				pstmt.setInt(2, id);
				pstmt.setInt(3, size);
			}
			rs = pstmt.executeQuery();
			while (rs.next()){
				Report r = new Report();
				r.setId(rs.getInt("id"));
				r.setType(rs.getInt("type"));
				r.setCategory(rs.getString("category"));
				r.setTitle(rs.getString("title"));
				r.setSource(rs.getString("source"));
				r.setAuthor(rs.getString("author"));
				r.setTime(rs.getString("time"));
				r.setContent(rs.getString("content"));
				r.setImage(rs.getString("image"));
				r.setUrl(rs.getString("url"));
				
				r.setCar_type(rs.getString("car_type"));
				r.setAgency(rs.getString("agency"));
				r.setOk(rs.getInt("ok"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return list;
	}
	
	public List<Object> getReports(int id, int size) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		conn = DBHelper.getConn();
		try {
			if (id == -1){
				sql = "select * from report where ok = 1 order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, size);
			}else{
				sql = "select * from report where id<=? && ok=1 order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, size);
			}
			rs = pstmt.executeQuery();
			while (rs.next()){
				Report r = new Report();
				r.setId(rs.getInt("id"));
				r.setType(rs.getInt("type"));
				r.setCategory(rs.getString("category"));
				r.setTitle(rs.getString("title"));
				r.setSource(rs.getString("source"));
				r.setAuthor(rs.getString("author"));
				r.setTime(rs.getString("time"));
				r.setContent(rs.getString("content"));
				r.setImage(rs.getString("image"));
				r.setUrl(rs.getString("url"));
				
				r.setCar_type(rs.getString("car_type"));
				r.setAgency(rs.getString("agency"));
				r.setOk(rs.getInt("ok"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
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

	public List<Object> getAll(int id, int size) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		conn = DBHelper.getConn();
		try {
			if (id == -1){
				sql = "select * from report order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, size);
			}else{
				sql = "select * from report where id<=? order by time desc limit ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setInt(2, size);
			}
			rs = pstmt.executeQuery();
			while (rs.next()){
				Report r = new Report();
				r.setId(rs.getInt("id"));
				r.setType(rs.getInt("type"));
				r.setCategory(rs.getString("category"));
				r.setTitle(rs.getString("title"));
				r.setSource(rs.getString("source"));
				r.setAuthor(rs.getString("author"));
				r.setTime(rs.getString("time"));
				r.setContent(rs.getString("content"));
				r.setImage(rs.getString("image"));
				r.setUrl(rs.getString("url"));
				
				r.setCar_type(rs.getString("car_type"));
				r.setAgency(rs.getString("agency"));
				r.setOk(rs.getInt("ok"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
		return list;
	}

	public void delReport(int id) {
		// TODO Auto-generated method stub
		conn = DBHelper.getConn();
		sql = "delete  from report where id=?";
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

	public void passReport(int id) {
		// TODO Auto-generated method stub
		conn = DBHelper.getConn();
		sql = "update report set ok = 1 where id=?";
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

}
