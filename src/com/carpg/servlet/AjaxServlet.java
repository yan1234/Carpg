package com.carpg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpg.dao.UserDao;
import com.carpg.impl.UserImpl;

public class AjaxServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AjaxServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�õ���Ҫ�����ajax��ҵ������,������֤�û����Ƿ���ڣ���½����֤��Ĵ���
		String type = request.getParameter("type");
		System.out.println("���ݵĲ���: "+type);
		String ok = "fail";
		//��ʾ����֤�û����Ƿ���ڵĲ���
		if (type.equals("username")){
			String username = request.getParameter("username");
			System.out.println("���ݵĲ���: "+username);
			UserDao userDao = new UserImpl();
			if (!userDao.checkUser(username)){
				//out.println("�û�����["+username+"]�Ѿ���ע�ᣬ����������û�������ע�ᡣ");
				ok = "success";
			}
		}//��ʾ������ajax��֤��½�Ĳ���
		else if (type.equals("login")){
			String check = "";
			//��½�ɹ�
			UserDao userDao = new UserImpl();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String code = request.getParameter("verify");
			//��ȡϵͳ�����ɵ���֤��
			String verify = (String)request.getSession().getAttribute("vcode");
			System.out.println("���ݵ���֤��: "+code +verify);
			//��ʾ��֤����ȷ
			if (code.toLowerCase().equals(verify.toLowerCase())){
				check = userDao.checkLogin(username, password);
				if (!check.equals("error")){
					System.out.println("��½�ɹ�");
					//���û���Ϣ������session��,�û���ϢΪuserid+user_name(����)
					Calendar c = Calendar.getInstance();
					String info = c.getTimeInMillis()+"~"+check;
					request.getSession().setAttribute("user", info);	
					ok = "success";
				}
			}
			
			
		}//��ʾ����֤��Ĵ������
		else if (type.equals("vcode")){
			//�õ���д����֤��
			String verifyTemp = request.getParameter("verify");
			//��ȡϵͳ�����ɵ���֤��
			String verify = (String)request.getSession().getAttribute("vcode");
			if (verify.toLowerCase().equals(verifyTemp.toLowerCase())){
				ok = "success";
			}
		}
		out.print(ok);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
