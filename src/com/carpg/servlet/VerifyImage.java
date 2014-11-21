package com.carpg.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public VerifyImage() {
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
		response.setContentType("image/jpeg");    
		response.setHeader("Pragma", "No-cache");    
		response.setHeader("Cache-Control", "no-cache");    
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession();    
		// ���ڴ��д���ͼ��    
		int width = 75, height = 25;    
		BufferedImage image = new BufferedImage(width, height,    
		        BufferedImage.TYPE_INT_RGB);    
		// ��ȡͼ��������    
		Graphics g = image.getGraphics();    
		// ���������    
		Random random = new Random();    
		// �趨����ɫ    
		g.setColor(getRandColor(200, 250));    
		g.fillRect(0, 0, width, height);    
		// �趨����    
		g.setFont(new Font("Times New Roman", Font.PLAIN, 24));    
		// ���߿�    
		g.setColor(getRandColor(160, 200));    
		g.drawRect(0, 0, width - 1, height - 1);    
		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽    
		g.setColor(getRandColor(160, 200));    
		for (int i = 0; i < 155; i++) {    
		    int x = random.nextInt(width);    
		    int y = random.nextInt(height);    
		    int xl = random.nextInt(12);    
		    int yl = random.nextInt(12);    
		    g.drawLine(x, y, x + xl, y + yl);    
		}    
		// ȡ�����������֤��(4λ����)    
		String sRand = "";    
		for (int i = 0; i < 4; i++) {    
		    String rand = getStringRandom(1);   
		    sRand += rand;    
		    // ����֤����ʾ��ͼ����    
		    g.setColor(new Color(20 + random.nextInt(110), 20 + random    
		            .nextInt(110), 20 + random.nextInt(110)));    
		    // ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������    
		    g.drawString(rand, 13 * i + 14, 20);    
		} 
		// ����֤�����SESSION    
		session.setAttribute("vcode", sRand); 
		// ͼ����Ч    
		g.dispose();    
		// ���ͼ��ҳ��    
		ImageIO.write(image, "JPEG", response.getOutputStream());
		
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
	
	Color getRandColor(int fc, int bc) {// ������Χ��������ɫ    
	    Random random = new Random();    
	    if (fc > 255)    
	        fc = 255;    
	    if (bc > 255)    
	        bc = 255;    
	    int r = fc + random.nextInt(bc - fc);    
	    int g = fc + random.nextInt(bc - fc);    
	    int b = fc + random.nextInt(bc - fc);    
	    return new Color(r, g, b);    
	}  
	
	//����������ֺ���ĸ,  
    public String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //����length����ʾ���ɼ�λ�����  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //�����ĸ��������  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //����Ǵ�д��ĸ����Сд��ĸ  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
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
