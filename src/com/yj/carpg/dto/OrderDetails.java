package com.yj.carpg.dto;


public class OrderDetails {
	private int odid;//订单详细的ID
	private String cname;
	private double cprice;
	//private int orderid;//订单
	private Orders orders;
	
	public int getOdid() {
		return odid;
	}
	public void setOdid(int odid) {
		this.odid = odid;
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
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
	
	
}
