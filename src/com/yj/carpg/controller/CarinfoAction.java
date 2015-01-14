package com.yj.carpg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;
import com.yj.carpg.dao.CarinfoDao;
import com.yj.carpg.dto.Carinfo;
import com.yj.carpg.dto.CarinfoItem;
import com.yj.carpg.impl.CarinfoImpl;

public class CarinfoAction implements ModelDriven<Carinfo>,SessionAware{
	
	private final String SUCC="success";
	private final String DETAILS="carDetails";
	private int pageIndex=1;//接收页面的第几页
	private int totalPage=1; //共可分为多少页
	private Carinfo carinfo=new Carinfo();
	private CarinfoDao carinfoDao=new CarinfoImpl();
	private List<Carinfo> lstCarinfo;
	private List<CarinfoItem> lstCarinfoItem=new ArrayList<CarinfoItem>();
	private Map<String,Object> session;
	
	/**
	 * 方法
	 */
	//分页
	public String pagingCarinfo(){
		lstCarinfo=carinfoDao.pagingCarinfo(pageIndex);
		
		//System.out.println("img------->"+lstCarinfo.get(0).getCimage()[0]);
		int len=carinfoDao.findAll().size();
		//System.out.println("len-->"+len);
		totalPage=(len%6==0)?(len/6):(len/6)+1; //共几页
		System.out.println("totalPage-->"+totalPage);
		System.out.println("test-->"+lstCarinfo.get(0).getCid()+"  "+lstCarinfo.get(0).getCconfig());
		return SUCC;
	}
	//根据id查找详单
	public String findById(){
		carinfo=carinfoDao.findById(carinfo);
		return DETAILS;
	}
	//处理提交车辆
	public String saleCar(){
		if(carinfoDao.saleCar(carinfo)){
			return SUCC;
		}
		return "saleCar";
	}
	//处理购物车
	@SuppressWarnings("unchecked")
	public String shoppingCar(){
		/*String s = carinfo.getCname();
		try {
			s = new String(s.getBytes("GBK"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("cid-->"+carinfo.getCid());
		System.out.println("cname-->"+carinfo.getCname());
		System.out.println("cprice-->"+carinfo.getCprice());
		System.out.println("cowner-->"+carinfo.getCowner());
		System.out.println("cimage-->"+carinfo.getCimage());
		System.out.println("csign-->"+carinfo.getCsign());
		System.out.println("cdetails-->"+carinfo.getCdetails());*/
		/*String str;
		try {
			str = java.net.URLDecoder.decode(carinfo.getCname(), "utf-8");
			String username = java.net.URLDecoder.decode(str);
			System.out.println("username--->"+username);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("cname-->"+carinfo.getCname());*/
		
		boolean flag=false; //购物车中没有此商品
		//一个商品记录
		CarinfoItem infoitem=new CarinfoItem();
		infoitem.setCarinfo(carinfo);
		infoitem.setCount(1);
		if(session.get("shopcar")!=null){
			//不是第一次装，获取之前的session
			lstCarinfoItem=(List<CarinfoItem>) session.get("shopcar");
			for(int i=0;i<lstCarinfoItem.size();i++){
				System.out.println("name-->"+lstCarinfoItem.get(i).getCarinfo().getCname());
				if(lstCarinfoItem.get(i).getCarinfo().getCid()==carinfo.getCid()){
					flag = true;  //置标记为有此商品
					infoitem.setCount(infoitem.getCount()+1);
					lstCarinfoItem.set(i, infoitem); //修改商品数量
					break;
				}
			}
		}
		if(!flag){ //购物车中没有此商品
			lstCarinfoItem.add(infoitem);
		}
		session.put("shopcar", lstCarinfoItem);
		return "shopcar";
	}

	/**
	 * 获取&共享
	 */
	public List<Carinfo> getLstCarinfo() {
		return lstCarinfo;
	}
	public Carinfo getCarinfo() {
		return carinfo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Carinfo getModel() {
		return carinfo;
	}
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
	public void setCarinfo(Carinfo carinfo) {
		this.carinfo = carinfo;
	}
	
}
