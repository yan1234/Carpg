package com.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.mail.MessagingException;

import com.carpg.dao.UserDao;
import com.carpg.dto.User;
import com.carpg.util.DBHelper;
import com.carpg.util.JavaMail;
import com.carpg.util.Tools;


public class UserImpl implements UserDao {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";

	public UserImpl() {
		// TODO Auto-generated constructor stub
	}

	public void Logout() {
		// TODO Auto-generated method stub
		
	}

	public boolean Regist(User user) {
		// TODO Auto-generated method stub
		boolean ok = false;
		//���û�����������
		String temp = Tools.getMD5(user.getPassword());
		user.setPassword(temp);
		//����������֤��code,�Ե�ǰʱ���������
		Calendar c = Calendar.getInstance();
		user.setCode(String.valueOf(c.getTimeInMillis()));
		conn = DBHelper.getConn();
		sql = "insert into user value(null, ?,?,?,?,?,?,?,0,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTel());
			pstmt.setString(5, user.getProvince());
			pstmt.setString(6, user.getCity());
			pstmt.setString(7, user.getSection());
			pstmt.setString(8, user.getCode());
			pstmt.setString(9, user.getName());
			pstmt.executeUpdate();
			//�����ʼ���֤��Ϣ
			JavaMail mail = new JavaMail();
			try {
				mail.sendVerify(user.getEmail(), user.getUsername(), user.getCode());
				ok = true;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
	}

	public boolean checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		boolean ok = false;
		//��Ҫ���MD5���ܵĹ���
		String psw = Tools.getMD5(password);
		conn = DBHelper.getConn();
		sql = "select * from user where username=? && password=? && state=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, psw);
			rs = pstmt.executeQuery();
			//�ҵ��û�
			if (rs.next()){
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
	}

	public boolean backPsw(String username, String email) {
		// TODO Auto-generated method stub
		boolean ok = false;
		Calendar c = Calendar.getInstance();
		String code = String.valueOf(c.getTimeInMillis());
		//����ƥ����������֤
		conn = DBHelper.getConn();
		sql = "update user set code=? where username=? && email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			int count = 0;
			count = pstmt.executeUpdate();
			//������֤�ɹ�
			if (count == 1){
				JavaMail mail = new JavaMail();
				try {
					mail.sendVerify(email, username, code);
					ok = true;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
	}

	public boolean checkUser(String username) {
		// TODO Auto-generated method stub
		boolean ok = false;
		//���в�ѯ�ж����ݿ��Ƿ������ͬ���û���
		conn = DBHelper.getConn();
		String sql = "select * from user where username=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				ok = true;
			}
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
	}

	public boolean verifyUser(String username, String code) {
		// TODO Auto-generated method stub
		boolean ok = false;
		conn = DBHelper.getConn();
		sql = "update user set state = 1 where username=? && code=? && state=0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, code);			
			int count = 0;
			count = pstmt.executeUpdate();
			//��ʾ��һ�����ݵ�state���ı�
			if (count == 1){
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
		
	}

	public boolean updatePsw(String username, String code, String newPsw) {
		// TODO Auto-generated method stub
		boolean ok = false;
		String temp = Tools.getMD5(newPsw);
		conn = DBHelper.getConn();
		sql = "update user set password=? where code=? && username=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp);
			int count = 0;
			count = pstmt.executeUpdate();
			if (count == 1){
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBHelper.close(rs, pstmt);
		return ok;
	}

}
