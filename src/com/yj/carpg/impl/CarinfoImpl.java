package com.yj.carpg.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yj.carpg.dao.CarinfoDao;
import com.yj.carpg.dto.Carinfo;
import com.carpg.util.DBHelper;

public class CarinfoImpl implements CarinfoDao{
	
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private String mySQL="";
	private List<Carinfo> lstCarinfo=null;

	public List<Carinfo> pagingCarinfo(int pageIndex) {
		conn=DBHelper.getConn();
		lstCarinfo=new ArrayList<Carinfo>();
		mySQL="SELECT * FROM (SELECT ROWNUM R,C.* FROM (SELECT * FROM CARINFO ORDER BY CID) C WHERE ROWNUM <=?) WHERE R>=?";
		try {
			ps=conn.prepareStatement(mySQL);
			ps.setInt(1, pageIndex*6);
			ps.setInt(2, (pageIndex-1)*6+1);
			rs=ps.executeQuery();
			while(rs.next()){  //第一条是行号！不取行号！
				Carinfo car=new Carinfo();
				car.setCid(rs.getInt("cId"));
				System.out.println("cid-->"+rs.getInt("cId"));
				car.setCsign(rs.getString("csign"));
				car.setCseries(rs.getString("cseries"));
				car.setCstyle(rs.getString("cstyle"));
				car.setCcolor(rs.getString("ccolor"));
				car.setClocal(rs.getString("clocal"));
				car.setCdetailedlocal(rs.getInt("cdetailedlocal"));
				car.setCregistertime(rs.getString("cregistertime"));
				car.setCmaintain(rs.getInt("cmaintain"));
				car.setCaccident(rs.getInt("caccident"));
				car.setCuse(rs.getInt("cuse"));
				car.setCannualsurvey(rs.getString("cannualsurvey"));
				car.setCcompulsoryInsurance(rs.getString("ccompulsoryInsurance"));
				car.setCinsurancebusiness(rs.getString("cinsurancebusiness"));
				car.setCusetax(rs.getString("cusetax"));
				car.setCname(rs.getString("cName"));
				car.setCconfig(rs.getString("cconfig"));
				car.setCprice(rs.getDouble("cprice"));
				car.setCuseprice(rs.getInt("cuseprice"));
				car.setCrent(rs.getInt("crent"));
				car.setCthirdparty(rs.getInt("cthirdparty"));
				car.setCtransfer(rs.getInt("ctransfer"));
				car.setCowner(rs.getString("cowner"));
				car.setCphone(rs.getString("cphone"));
				car.setCcalltime(rs.getString("ccalltime"));
				car.setCregistration(rs.getString("cregistration"));
				car.setCdrivinglicense(rs.getString("cdrivinglicense"));
				car.setCreceipt(rs.getString("creceipt"));
				System.out.println(rs.getString("cimage"));
				String[] img=rs.getString("cimage").split(",");
				for(int i=0;i<img.length;i++){
					System.out.println(img[i]);
				}
				car.setCimage(img);
				car.setCmileage(rs.getInt("cmileage"));
				car.setCqq(rs.getString("cqq"));
				car.setCaddress(rs.getString("caddress"));
				car.setCdetails(rs.getString("cdetails"));
				lstCarinfo.add(car);
			}
			DBHelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCarinfo;
	}

	public Carinfo findById(Carinfo carinfo) {
		conn=DBHelper.getConn();
		mySQL="SELECT * FROM CARINFO WHERE CID=?";
		Carinfo car=null;
		try {
			ps=conn.prepareStatement(mySQL);
			ps.setInt(1, carinfo.getCid());
			rs=ps.executeQuery();
			if(rs.next()){  //有第一条
				car=new Carinfo();
				car.setCid(rs.getInt("cId"));
				car.setCsign(rs.getString("csign"));
				car.setCseries(rs.getString("cseries"));
				car.setCstyle(rs.getString("cstyle"));
				car.setCcolor(rs.getString("ccolor"));
				car.setClocal(rs.getString("clocal"));
				car.setCdetailedlocal(rs.getInt("cdetailedlocal"));
				car.setCregistertime(rs.getString("cregistertime"));
				car.setCmaintain(rs.getInt("cmaintain"));
				car.setCaccident(rs.getInt("caccident"));
				car.setCuse(rs.getInt("cuse"));
				car.setCannualsurvey(rs.getString("cannualsurvey"));
				car.setCcompulsoryInsurance(rs.getString("ccompulsoryInsurance"));
				car.setCinsurancebusiness(rs.getString("cinsurancebusiness"));
				car.setCusetax(rs.getString("cusetax"));
				car.setCname(rs.getString("cName"));
				car.setCconfig(rs.getString("cconfig"));
				car.setCprice(rs.getDouble("cprice"));
				car.setCuseprice(rs.getInt("cuseprice"));
				car.setCrent(rs.getInt("crent"));
				car.setCthirdparty(rs.getInt("cthirdparty"));
				car.setCtransfer(rs.getInt("ctransfer"));
				car.setCowner(rs.getString("cowner"));
				car.setCphone(rs.getString("cphone"));
				car.setCcalltime(rs.getString("ccalltime"));
				car.setCregistration(rs.getString("cregistration"));
				car.setCdrivinglicense(rs.getString("cdrivinglicense"));
				car.setCreceipt(rs.getString("creceipt"));
				System.out.println(rs.getString("cimage"));
				String[] img=rs.getString("cimage").split(",");
				for(int i=0;i<img.length;i++){
					System.out.println(img[i]);
				}
				car.setCimage(img);
				car.setCmileage(rs.getInt("cmileage"));
				car.setCqq(rs.getString("cqq"));
				car.setCaddress(rs.getString("caddress"));
				car.setCdetails(rs.getString("cdetails"));
			}
			DBHelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	public List<Carinfo> findAll() {
		conn=DBHelper.getConn();
		lstCarinfo=new ArrayList<Carinfo>();
		mySQL="SELECT * FROM CARINFO";
		try {
			ps=conn.prepareStatement(mySQL);
			rs=ps.executeQuery();
			while(rs.next()){  
				Carinfo car=new Carinfo();
				car.setCid(rs.getInt("cId"));
				car.setCsign(rs.getString("csign"));
				car.setCseries(rs.getString("cseries"));
				car.setCstyle(rs.getString("cstyle"));
				car.setCcolor(rs.getString("ccolor"));
				car.setClocal(rs.getString("clocal"));
				car.setCdetailedlocal(rs.getInt("cdetailedlocal"));
				car.setCregistertime(rs.getString("cregistertime"));
				car.setCmaintain(rs.getInt("cmaintain"));
				car.setCaccident(rs.getInt("caccident"));
				car.setCuse(rs.getInt("cuse"));
				car.setCannualsurvey(rs.getString("cannualsurvey"));
				car.setCcompulsoryInsurance(rs.getString("ccompulsoryInsurance"));
				car.setCinsurancebusiness(rs.getString("cinsurancebusiness"));
				car.setCusetax(rs.getString("cusetax"));
				car.setCname(rs.getString("cName"));
				car.setCconfig(rs.getString("cconfig"));
				car.setCprice(rs.getDouble("cprice"));
				car.setCuseprice(rs.getInt("cuseprice"));
				car.setCrent(rs.getInt("crent"));
				car.setCthirdparty(rs.getInt("cthirdparty"));
				car.setCtransfer(rs.getInt("ctransfer"));
				car.setCowner(rs.getString("cowner"));
				car.setCphone(rs.getString("cphone"));
				car.setCcalltime(rs.getString("ccalltime"));
				car.setCregistration(rs.getString("cregistration"));
				car.setCdrivinglicense(rs.getString("cdrivinglicense"));
				car.setCreceipt(rs.getString("creceipt"));
				String[] img=rs.getString("cimage").split(",");
				for(int i=0;i<img.length;i++){
					System.out.println(img[i]);
				}
				car.setCimage(img);
				car.setCmileage(rs.getInt("cmileage"));
				car.setCqq(rs.getString("cqq"));
				car.setCaddress(rs.getString("caddress"));
				car.setCdetails(rs.getString("cdetails"));
				lstCarinfo.add(car);
			}
			DBHelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCarinfo;
	}

	public boolean saleCar(Carinfo carinfo) {
		conn=DBHelper.getConn();
		mySQL="INSERT INTO CARINFO VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(mySQL);
			ps.setInt(1, carinfo.getCid());
			ps.setString(2, carinfo.getCsign());
			ps.setString(3, carinfo.getCseries());
			ps.setString(4, carinfo.getCstyle());
			ps.setString(5, carinfo.getCcolor());
			ps.setString(6, carinfo.getClocal());
			ps.setInt(7, carinfo.getCdetailedlocal());
			ps.setString(8, carinfo.getCregistertime());
			ps.setInt(9, carinfo.getCmaintain());
			ps.setInt(10, carinfo.getCaccident());
			ps.setInt(11, carinfo.getCuse());
			ps.setString(12, carinfo.getCannualsurvey());
			ps.setString(13, carinfo.getCcompulsoryInsurance());
			ps.setString(14, carinfo.getCinsurancebusiness());
			ps.setString(15, carinfo.getCusetax());
			ps.setString(16, carinfo.getCname());
			ps.setString(17, carinfo.getCconfig());
			ps.setDouble(18, carinfo.getCprice());
			ps.setInt(19, carinfo.getCuseprice());
			ps.setInt(20, carinfo.getCrent());
			ps.setInt(21, carinfo.getCthirdparty());
			ps.setInt(22, carinfo.getCtransfer());
			ps.setString(23, carinfo.getCowner());
			ps.setString(24, carinfo.getCphone());
			ps.setString(25, carinfo.getCcalltime());
			ps.setString(26, carinfo.getCregistration());
			ps.setString(27, carinfo.getCdrivinglicense());
			ps.setString(28, carinfo.getCreceipt());
			ps.setString(29, carinfo.getCimage()[0]);
			ps.setInt(30, carinfo.getCmileage());
			ps.setString(31, carinfo.getCqq());
			ps.setString(32, carinfo.getCaddress());
			ps.setString(33, carinfo.getCdetails());
			rs=ps.executeQuery();
			if(rs.next()){  //有一条
				return true;
			}
			DBHelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



}
