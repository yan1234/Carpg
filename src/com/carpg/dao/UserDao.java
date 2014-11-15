package com.carpg.dao;

import com.carpg.dto.User;

public interface UserDao {
	
	//��½��֤,���ݲ���Ϊ�û���������
	public boolean checkLogin(String username, String password);
	
	//�û����Ƿ���ڼ�⣬��Ҫ����ǰ̨��ajax��֤
	public boolean checkUser(String username);
	
	//ע�ᣬ���ݲ���Ϊ�û���ϢUser����
	public boolean Regist(User user);
	
	//������֤,���ݲ���Ϊ�û�����ƥ���code��
	public boolean verifyUser(String username, String code);
	
	//�����ʼ��һ�����,����Ϊ�û����Ͱ�����,�����޸�����������֤
	public boolean backPsw(String username, String email);
	
	//�һ����������޸�,����Ϊ��֤ƥ���code��������
	public boolean updatePsw(String username, String code, String newPsw);
	
	//�˳�
	public void Logout();

}
