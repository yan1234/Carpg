package com.yj.carpg.dto;

import java.io.Serializable;

public class Carinfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	private int cid;
	private String csign;   //车品牌
	private String cseries; //车系
	private String cstyle;  //款式
	private String ccolor;  //车辆颜色
	private String clocal;  //车辆所在地
	private int cdetailedlocal; //市内还是城镇
	private String cregistertime; //首次上牌时间
	private int cmaintain;  //是否定期4s保养
	private int caccident;  //有无重大事故
	private int cuse;//家用or公用
	private String cannualsurvey; //年检
	private String ccompulsoryInsurance; //交强险
	private String cinsurancebusiness;   //商业险
	private String cusetax;  //车船使用税有效期
	private String cname;    //车名
	private String cconfig;  //车辆配置
	private double cprice;   //转让价格
	private int cuseprice;//包含过户费
	private int crent;//是否同意租出
	private int cthirdparty; //是否接受第三方验车
	private int ctransfer;   //过户次数
	private String cowner;   //车主
	private String cphone;   //联系电话
	private String ccalltime;//允许联系时间
	private String cregistration;  //登记证
	private String cdrivinglicense;//行驶证
	private String creceipt;//购车发票
	private String[] cimage;  //图片
	private int cmileage;   //行驶里程
	private String cqq;  //qq号码
	private String caddress;//看车地址
	private String cdetails;
	
	
	
	public String[] getCimage() {
		return cimage;
	}
	public void setCimage(String[] cimage) {
		this.cimage = cimage;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCsign() {
		return csign;
	}
	public void setCsign(String csign) {
		this.csign = csign;
	}
	public String getCseries() {
		return cseries;
	}
	public void setCseries(String cseries) {
		this.cseries = cseries;
	}
	public String getCstyle() {
		return cstyle;
	}
	public void setCstyle(String cstyle) {
		this.cstyle = cstyle;
	}
	public String getCcolor() {
		return ccolor;
	}
	public void setCcolor(String ccolor) {
		this.ccolor = ccolor;
	}
	public String getCregistertime() {
		return cregistertime;
	}
	public void setCregistertime(String cregistertime) {
		this.cregistertime = cregistertime;
	}
	public int getCmaintain() {
		return cmaintain;
	}
	public void setCmaintain(int cmaintain) {
		this.cmaintain = cmaintain;
	}
	public int getCaccident() {
		return caccident;
	}
	public void setCaccident(int caccident) {
		this.caccident = caccident;
	}
	public String getCannualsurvey() {
		return cannualsurvey;
	}
	public void setCannualsurvey(String cannualsurvey) {
		this.cannualsurvey = cannualsurvey;
	}
	public String getCcompulsoryInsurance() {
		return ccompulsoryInsurance;
	}
	public void setCcompulsoryInsurance(String ccompulsoryInsurance) {
		this.ccompulsoryInsurance = ccompulsoryInsurance;
	}
	public String getCinsurancebusiness() {
		return cinsurancebusiness;
	}
	public void setCinsurancebusiness(String cinsurancebusiness) {
		this.cinsurancebusiness = cinsurancebusiness;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getCprice() {
		return cprice;
	}
	public void setCprice(double cprice) {
		this.cprice = cprice;
	}
	public String getCowner() {
		return cowner;
	}
	public void setCowner(String cowner) {
		this.cowner = cowner;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public int getCmileage() {
		return cmileage;
	}
	public void setCmileage(int cmileage) {
		this.cmileage = cmileage;
	}
	public String getCqq() {
		return cqq;
	}
	public void setCqq(String cqq) {
		this.cqq = cqq;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCdetails() {
		return cdetails;
	}
	public void setCdetails(String cdetails) {
		this.cdetails = cdetails;
	}
	public String getClocal() {
		return clocal;
	}
	public void setClocal(String clocal) {
		this.clocal = clocal;
	}
	public int getCdetailedlocal() {
		return cdetailedlocal;
	}
	public void setCdetailedlocal(int cdetailedlocal) {
		this.cdetailedlocal = cdetailedlocal;
	}
	public int getCuse() {
		return cuse;
	}
	public void setCuse(int cuse) {
		this.cuse = cuse;
	}
	public String getCusetax() {
		return cusetax;
	}
	public void setCusetax(String cusetax) {
		this.cusetax = cusetax;
	}
	public String getCconfig() {
		return cconfig;
	}
	public void setCconfig(String cconfig) {
		this.cconfig = cconfig;
	}
	public int getCuseprice() {
		return cuseprice;
	}
	public void setCuseprice(int cuseprice) {
		this.cuseprice = cuseprice;
	}
	public int getCrent() {
		return crent;
	}
	public void setCrent(int crent) {
		this.crent = crent;
	}
	public int getCthirdparty() {
		return cthirdparty;
	}
	public void setCthirdparty(int cthirdparty) {
		this.cthirdparty = cthirdparty;
	}
	public int getCtransfer() {
		return ctransfer;
	}
	public void setCtransfer(int ctransfer) {
		this.ctransfer = ctransfer;
	}
	public String getCcalltime() {
		return ccalltime;
	}
	public void setCcalltime(String ccalltime) {
		this.ccalltime = ccalltime;
	}
	public String getCregistration() {
		return cregistration;
	}
	public void setCregistration(String cregistration) {
		this.cregistration = cregistration;
	}
	public String getCdrivinglicense() {
		return cdrivinglicense;
	}
	public void setCdrivinglicense(String cdrivinglicense) {
		this.cdrivinglicense = cdrivinglicense;
	}
	public String getCreceipt() {
		return creceipt;
	}
	public void setCreceipt(String creceipt) {
		this.creceipt = creceipt;
	}
	
	
}
