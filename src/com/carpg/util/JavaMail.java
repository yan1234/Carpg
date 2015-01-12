package com.carpg.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
	
	private final String username="tao_baoguang@163.com";
	private final String password="taobaoguang1";
	
	 private Message getMessage(){
	  Properties p=new Properties();
	  p.put("mail.transport.protocol","smtp");
	  p.put("mail.smtp.host","smtp.163.com");
	  p.put("mail.smtp.port","25");
	  p.put("mail.smtp.auth","true");
	  
	  MyAuth auth = new MyAuth(username, password);
	  Session session=Session.getInstance(p, auth);
	  Message message=new MimeMessage(session);
	  return message;
	 }
	 
	 //发送邮箱验证信息,根据type类型表示验证的操作,"regist",和"return_password"
	 public void sendVerify(String email,String name,String code,String type)
	 throws MessagingException {	 
	   Message message=getMessage();
	   message.setFrom(new InternetAddress(username));
	   message.setRecipient(RecipientType.TO,new InternetAddress(email));
	   message.setSentDate(new Date());
	   message.setSubject("Carpg");
	   String m="<a href="+"http://120.24.211.77:8080/Carpg/servlet/MailServlet.sl?name="+email+"&type="+type+"&randMd5="+code+">" +
	     "http://120.24.211.77:8080/Carpg/servlet/MailServlet.sl?name="+email+"&type="+type+"&randMd5="+code+"</a>";
	   message.setContent(m,"text/html;charset=utf-8");
	   Transport.send(message);
	  }

}
