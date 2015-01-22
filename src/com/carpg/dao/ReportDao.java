package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Report;

public interface ReportDao {
	
	//报告编辑发布功能
	public void addReport(Report report);
	
	//报告信息展示功能,返回信息列表, 根据需要展示的类别type获取size条信息, 0:汽车调查报告；1:召回信息
		//id为需要获取的最新的一条的id值，用于分页展示；-1:为表示获取当前最新的值
	public List<Object> getReportsByType(int type, int id, int size);
	
	//报告信息展示功能,返回信息列表(size条)
	//id为需要获取的最新的一条的id值，用于分页展示；-1:为表示获取当前最新的值
	public List<Object> getReports(int id, int size);
	
	//管理员获取所有的报告便于审核
	public List<Object> getAll(int id, int size);
	
	//审核通过报告信息
	public void passReport(int id);
	
	//删除报告信息
	public void delReport(int id);
	

}
