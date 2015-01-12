package com.yj.carpg.test;

import java.sql.SQLException;

import com.carpg.util.DBHelper;


import junit.framework.TestCase;

public class TestAll extends TestCase{

	public void testConn(){
		try {
			System.out.println(DBHelper.getConn().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
