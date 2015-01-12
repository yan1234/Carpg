package com.carpg.dto;

public class Userinfo {
	private int userid;         //编号
	private String username;    //用户名，不为空，并且唯一,用邮箱代替
	private String userpwd;     //密码
	private String email;       //邮箱
	private String tel;         //联系方式和邮箱用于注册验证，至少选填一个
	private String province;    //省份， 不为空
	private String city;        //城市， 不为空
	private String usersection; //区
	private int state;          //用户状态，0:未验证，1：验证成功,主要用于验证用户注册
	private String usercode;    //激活码,用于注册验证时匹配使用
	private String useralias;   //用户别名
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUsersection() {
		return usersection;
	}
	public void setUsersection(String usersection) {
		this.usersection = usersection;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUseralias() {
		return useralias;
	}
	public void setUseralias(String useralias) {
		this.useralias = useralias;
	}
	
	
}
