package com.carpg.dao;

import java.util.List;

import com.carpg.dto.Car;

public interface CarDao {
	
	//ȡ�����г��͵���Ϣ
	public List<Car> getCars();
	
	//�жϵ�ǰ�����Ƿ����,�����򷵻ظó��͵�id, ���򷵻�-1
	public int isExist(Car car);
	
	//��ӳ���,���س���id
	public int addCar(Car car);

}
