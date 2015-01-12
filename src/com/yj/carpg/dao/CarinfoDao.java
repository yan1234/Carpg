package com.yj.carpg.dao;

import java.util.List;

import com.yj.carpg.dto.Carinfo;

public interface CarinfoDao {
	//分页查询car
	public List<Carinfo> pagingCarinfo(int pageIndex);
	//根据id查询某一辆车
	public Carinfo findById(Carinfo carinfo);
	//查询所有车
	public List<Carinfo> findAll();
	//处理提交车辆
	public boolean saleCar(Carinfo carinfo);

}
