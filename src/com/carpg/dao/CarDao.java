package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Car;

public interface CarDao {
	
	//ȡ�����г��͵���Ϣ
	public List<Car> getCars();
	
	//�жϵ�ǰ�����Ƿ����
	public boolean isExist(Car car);
	
	//��ӳ���
	public void addCar(Car car);

}
