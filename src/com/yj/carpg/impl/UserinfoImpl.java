package com.yj.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yj.carpg.dao.UserinfoDao;
import com.yj.carpg.dto.Userinfo;
import com.carpg.util.DBHelper;

public class UserinfoImpl implements UserinfoDao{
	
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private String mySQL="";

	public boolean checkLogin(Userinfo userinfo) {
		conn=DBHelper.getConn();
		mySQL="SELECT USERNAME FROM USER WHERE USERNAME=? AND password=?";
		try {
			ps=conn.prepareStatement(mySQL);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getUserpwd());
			
			rs=ps.executeQuery();
			if(rs.next()){ //查询到了该用户
				return true;  //返回登录成功
			}
			DBHelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean logout() {
		return false;
	}

	public boolean checkRegister(Userinfo userinfo) {
		return false;
	}

}
