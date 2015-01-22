package com.carpg.util;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

public class JavaMail {
	
	//表示邮件发送的配置
	private String transport = null;
	private String host = null;
	private String port = null;
	private String auth = null;
	private String username=null;
	private String password=null;
	
	//表示邮件处理的servlet对象路径
	private String url;

	private Logger log = Logger.getLogger(this.getClass());
	private void config(){
		//获取配置文件
		ResourceBundle resources = ResourceBundle.getBundle("javamail");
		transport = resources.getString("transport").trim();
		host = resources.getString("host").trim();
		port = resources.getString("port").trim();
		auth = resources.getString("auth").trim();
		username = resources.getString("username").trim();
		password = resources.getString("password").trim();
		url = resources.getString("url").trim();
		
		log.info("获取javamail的配置文件"+transport+host+port+auth+username+password+url);
		
	}
	 private Message getMessage(){
		 //取出配置信息
		 config();
	  Properties p=new Properties();
	  p.put("mail.transport.protocol",transport);
	  p.put("mail.smtp.host",host);
	  p.put("mail.smtp.port",port);
	  p.put("mail.smtp.auth",auth);
	  
	  MyAuth auth = new MyAuth(username, password);
	  Session session=Session.getInstance(p, auth);
	  Message message=new MimeMessage(session);
	  return message;
	 }
	 
	 //发送邮箱验证信息,根据type类型表示验证的操作,"regist",和"return_password"
	 public void sendVerify(String email,String name,String code,String type){	 
	   Message message=getMessage();
	   log.info("封装的消息体:");
	   log.info(message);
	   try {
		message.setFrom(new InternetAddress(username));
		message.setRecipient(RecipientType.TO,new InternetAddress(email));
		   message.setSentDate(new Date());
		   message.setSubject("Carpg");
		   String m="<a href="+url+"/servlet/MailServlet.sl?name="+email+"&type="+type+"&randMd5="+code+">" +
		     url+"/servlet/MailServlet.sl?name="+email+"&type="+type+"&randMd5="+code+"</a>";
		   message.setContent(m,"text/html;charset=utf-8");
		   Transport.send(message);
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		log.info("AddressException:");
		log.info(e);
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		log.info("MessageException");
		log.info(e);
		e.printStackTrace();
	}
	   
	  }

}
