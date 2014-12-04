package com.carpg.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FileServlet() {
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

		this.doPost(request, response);
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
		
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html; charset=UTF-8");  
		  
		//����·��  
		String savePath = getServletContext().getRealPath("/upload");  
		File saveDir = new File(savePath);  
		// ���Ŀ¼�����ڣ��ʹ���Ŀ¼  
		if(!saveDir.exists()){  
		    saveDir.mkdir();  
		}  
		  
		// �����ļ��ϴ������� 
		
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		ServletFileUpload sfu = new ServletFileUpload(factory); 
		//DiskFileUpload sfu = new DiskFileUpload();
		//���ñ���  
		sfu.setHeaderEncoding("UTF-8");  
		// �����ϴ��ĵ����ļ�������ֽ���Ϊ2M  
		sfu.setFileSizeMax(1024*1024*2);  
		//����������������ֽ���Ϊ10M  
		sfu.setSizeMax(1024*1024*10);
		try{  
		    // ���������  
		    List<FileItem> itemList = sfu.parseRequest(request);  
		    for (FileItem fileItem : itemList) {  
		        // ��Ӧ���еĿؼ���name  
		        String fieldName = fileItem.getFieldName();  
		        System.out.println("�ؼ����ƣ�" + fieldName);  
		        // �������ͨ���ؼ�  
		        if(fileItem.isFormField()){  
		            String value = fileItem.getString();  
		            //���±���,�������  
		            value = new String(value.getBytes("ISO-8859-1"),"UTF-8");  
		            System.out.println("��ͨ���ݣ�" + fieldName + "=" + value);  
		        // �ϴ��ļ�  
		        }else{  
		            // ����ļ���С  
		            Long size = fileItem.getSize();  
		            // ����ļ���  
		            String fileName = fileItem.getName();  
		            System.out.println("�ļ�����"+fileName+"\t��С��" + size + "byte");  
		              
		            //���ò������ϴ����ļ���ʽ  
		            if(fileName.endsWith(".exe")){  
		                request.setAttribute("msg", "�������ϴ������ͣ�");  
		            }else{  
		                //���ļ����浽ָ����·��  
		                File file = new File(savePath,fileName);  
		                fileItem.write(file);  
		                request.setAttribute("msg", "�ϴ��ɹ���");  
		            }  
		        }  
		    }  
		}catch(FileSizeLimitExceededException e){  
		    request.setAttribute("msg", "�ļ�̫��");  
		}catch(FileUploadException e){  
		    e.printStackTrace();  
		}catch(Exception e){  
		    e.printStackTrace();
		}
		
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
