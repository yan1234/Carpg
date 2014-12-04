package com.carpg.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 // ��ȡuri��ַ
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String uri = request.getRequestURI();
        String context=request.getContextPath();
        uri = uri.substring(context.length());
        System.out.println("������"+ request.getSession().getAttribute("sessioninfo"));
        if(null == request.getSession().getAttribute("sessioninfo")) {
            request.setAttribute("message","��û�����Ȩ��");
            System.out.println("Ȩ�޲���");
            //request.getRequestDispatcher("/JSP/login.jsp").forward(arg0,arg1);
            response.sendRedirect("/JSP/login.jsp");
            return;
        }
        else{
        	//�����жϳɹ����������������Ŀ�ĵ�ַ
        	arg2.doFilter(arg0, arg1);
        }
      //�ж�admin������ҳ�����Ȩ��
        if(uri.startsWith("/admin")) {
            
        }
        //�ж�manage������ҳ�����Ȩ��
        if(uri.startsWith("/manage")) {
            //����ʡȥ
            }
        //���滹��������������û�Ȩ�ޣ�ʡȥ��
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
