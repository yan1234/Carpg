package com.carpg.dao;

import java.sql.SQLException;
import java.util.List;

import com.carpg.dto.Car_Problem;

public interface Car_ProblemDao {
	
	//�õ�������б�
	public List<Car_Problem> getCar_Problems();
	
	//����µ��������
	public void addCar_Problem(Car_Problem carProblem);
	
	

}
