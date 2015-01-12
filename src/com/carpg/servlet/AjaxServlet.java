package com.carpg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carpg.dao.UserinfoDao;
import com.carpg.impl.UserinfoImpl;

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
		//得到需要处理的ajax的业务类型,包括验证用户名是否存在，登陆，验证码的处理
		String type = request.getParameter("type");
		System.out.println("传递的参数: "+type);
		String ok = "fail";
		//表示是验证用户名是否存在的操作
		if (type.equals("username")){
			String username = request.getParameter("username");
			System.out.println("传递的参数: "+username);
			UserinfoDao userDao = new UserinfoImpl();
			if (!userDao.checkUser(username)){
				//out.println("用户名称["+username+"]已经被注册，请更换其他用户名称再注册。");
				ok = "success";
			}
		}//表示是利用ajax验证登陆的操作
		else if (type.equals("login")){
			String check = "";
			//登陆成功
			UserinfoDao userDao = new UserinfoImpl();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String code = request.getParameter("verify");
			//获取系统中生成的验证码
			String verify = (String)request.getSession().getAttribute("vcode");
			System.out.println("传递的验证码: "+code +verify);
			//表示验证码正确
			if (code.toLowerCase().equals(verify.toLowerCase())){
				check = userDao.checkLogin(username, password);
				if (!check.equals("error")){
					System.out.println("登陆成功");
					//将用户信息保存在session中,用户信息为userid+user_name(别名)
					Calendar c = Calendar.getInstance();
					String info = c.getTimeInMillis()+"~"+check;
					request.getSession().setAttribute("user", info);	
					ok = "success";
				}
			}
			
			
		}//表示是验证码的处理操作
		else if (type.equals("vcode")){
			//得到填写的验证码
			String verifyTemp = request.getParameter("verify");
			//获取系统中生成的验证码
			String verify = (String)request.getSession().getAttribute("vcode");
			if (verify.toLowerCase().equals(verifyTemp.toLowerCase())){
				ok = "success";
			}
		}
		System.out.println("ok: "+ok);
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
