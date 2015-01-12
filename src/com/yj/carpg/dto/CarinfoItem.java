package com.yj.carpg.dto;


//存购物车一条数据
public class CarinfoItem implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Carinfo carinfo;
	private int count;
	
	public Carinfo getCarinfo() {
		return carinfo;
	}
	public void setCarinfo(Carinfo carinfo) {
		this.carinfo = carinfo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
