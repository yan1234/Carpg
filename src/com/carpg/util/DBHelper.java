package com.carpg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;


public class DBHelper {
	
	//数据库连接驱动
    private static String driver = null;
    // 数据库连接url
    private static String url = null;
    // 用户名
    private static String user = null; 
    // 密码
    private static String password = null;
    private static Connection conn = null;
    
    // 创建数据库连接（单例模式）
	public static Connection getConn() {
		try {
			//得到数据库的配置信息
			getConfig();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return conn;
	}
	//取出全局变量中的参数设置
	private static void getConfig(){
		ServletContext application = ServletActionContext.getServletContext();
		driver = (String)application.getAttribute("driver");
		url = (String)application.getAttribute("url");
		user = (String)application.getAttribute("user");
		password = (String)application.getAttribute("password");
		//判断全局变量是否存在
		if (driver == null || url == null || user == null || password == null){
			ResourceBundle resources = ResourceBundle.getBundle("jdbc");
			driver = resources.getString("driver").trim();
			url = resources.getString("url").trim();
			user = resources.getString("user").trim();
			password = resources.getString("password").trim();
		}
		
	}
	// 关闭连接
	public static void close() {
		if (null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    

}
