package com.carpg.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private static final String CONTENT_TYPE = "text/html; charset=utf-8"; 
	public static Logger logger = Logger.getLogger(Log4jInit.class.getName()); 
	
	public Log4jInit() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
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
		super.init();
		//ͨ��web.xml����̬ȡ�������ļ� 
		String path = getServletContext().getRealPath("/"); 
		String file = getInitParameter("log4j-init-file");
		String logFile=this.getInitParameter("log4j-log-path");  
		System.out.println("log4j: "+file);
		// ���û�и�����Ӧ�������ļ����򲻽��г�ʼ�� 
		if(file!=null) {   
		    Properties prop = new Properties();    
		    try{   
		        prop.load(new FileInputStream(path+file)); //����log4j.properties   
		        prop.setProperty("log4j.appender.A2.File", path+logFile+ prop.getProperty("log4j.appender.A2.File")); //������־�ļ������·��   
		    	PropertyConfigurator.configure(prop); //����������   
			}catch(Exception e) {   
		    	logger.info("��ʼ��log4j��־����·���쳣������web.xml���������Ƿ��������쳣������"+this.getClass().getName()+"���public void init()�������쳣��Ը���ǣ�"+e.getMessage(), e.fillInStackTrace());       
		    	}   
		}
	}

}
