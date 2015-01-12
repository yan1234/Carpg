package com.carpg.dao;

import com.carpg.dto.User;
import com.carpg.dto.Userinfo;

public interface UserinfoDao {
	
	//��½��֤,���ݲ���Ϊ�û���������,����error��ʾ��½ʧ�ܣ������û�id+name��ʾ��½�ɹ�
	public String checkLogin(String username, String password);
	
	//�û����Ƿ���ڼ�⣬��Ҫ����ǰ̨��ajax��֤
	public boolean checkUser(String username);
	
	//ע�ᣬ���ݲ���Ϊ�û���ϢUser����
	public boolean Regist(Userinfo user);
	
	//������֤,���ݲ���Ϊ�û�����ƥ���code��
	public boolean verifyUser(String username, String code);
	
	//�����ʼ��һ�����,����Ϊ������,�����޸�����������֤
	public boolean backPsw(String email);
	
	//�һ����������޸�,����Ϊ��֤ƥ���code��������
	public boolean updatePsw(String username, String code, String newPsw);
	
	//�˳�
	public void Logout();

}
