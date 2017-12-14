package com.tf.base.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSenderUtil {
	
	
	private String smtpHost;
	private String addresser;
	private String addresserPwd;

	

	/**
	 * 
	 * 发送邮件
	 * @param addressees	收件人邮箱地址数组
	 * @param mailSubject	邮件主题
	 * @param mailText		邮件内容
	 * @param sessionDebug	是否在控制打印控制信息
	 * 
	 */
	public void send(String[] addressees,String mailSubject,String mailText,boolean sessionDebug){
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", this.smtpHost);
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props);
	    session.setDebug(sessionDebug);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(this.addresser);
	        //数组转换字符串
	        if(null != addressees){
	        	String toList = getMailList(addressees); 
	        	InternetAddress[] adds = InternetAddress.parse(toList);
	        	msg.setRecipients(Message.RecipientType.TO,adds);
	        }
	        msg.setSubject(mailSubject);
	        msg.setSentDate(new Date());
	        msg.setText(mailText);
	        Transport.send(msg,this.addresser,this.addresserPwd);
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * 发送邮件
	 * @param smtpHost	smtp服务器
	 * @param addresser	发件人邮箱地址
	 * @param addresserPwd 发件人邮箱密码
	 * @param addressees	收件人邮箱地址数组
	 * @param mailSubject	邮件主题
	 * @param mailText		邮件内容
	 * @param sessionDebug	是否在控制打印控制信息
	 * 
	 */
	public static void send(String smtpHost,String addresser,String addresserPwd,
			String[] addressees,String mailSubject,String mailText,boolean sessionDebug){
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props);
	    session.setDebug(sessionDebug);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(addresser);
	        //数组转换字符串
	        if(null != addressees){
	        	String toList = getMailList(addressees); 
	        	InternetAddress[] adds = InternetAddress.parse(toList);
	        	msg.setRecipients(Message.RecipientType.TO,adds);
	        }
	        msg.setSubject(mailSubject);
	        msg.setSentDate(new Date());
	        msg.setText(mailText);
	        Transport.send(msg,addresser,addresserPwd);
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * 发送邮件-可以抄送
	 * @param smtpHost	smtp服务器
	 * @param addresser	发件人邮箱地址
	 * @param addresserPwd 发件人邮箱密码
	 * @param addressees	收件人邮箱地址数组
	 * @param carbonCopyStrs	抄送人邮箱地址数组
	 * @param mailSubject	邮件主题
	 * @param mailText		邮件内容
	 * @param sessionDebug	是否在控制打印控制信息
	 * 
	 */
	public static void send(String smtpHost,String addresser,String addresserPwd,
			String[] addressees,String[] carbonCopyStrs,String mailSubject,String mailText,boolean sessionDebug){
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props);
	    session.setDebug(sessionDebug);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(addresser);
	        //数组转换字符串,收件人
	        if(null != addressees){
	        	String toList = getMailList(addressees); 
	        	InternetAddress[] adds = InternetAddress.parse(toList);
	        	msg.setRecipients(Message.RecipientType.TO,adds);
	        }
	        //数组转换字符串,抄送
	        if(null != carbonCopyStrs){
	        	String ccList = getMailList(carbonCopyStrs); 
		        InternetAddress[] ccs = InternetAddress.parse(ccList);
		        msg.setRecipients(Message.RecipientType.CC,ccs);
	        }
	        msg.setSubject(mailSubject);
	        msg.setSentDate(new Date());
	        msg.setText(mailText);
	        Transport.send(msg,addresser,addresserPwd);
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * 发送邮件-可以抄送
	 * @param smtpHost	smtp服务器
	 * @param addresser	发件人邮箱地址
	 * @param addresserPwd 发件人邮箱密码
	 * @param addressees	收件人邮箱地址数组
	 * @param carbonCopyStrs	抄送人邮箱地址数组
	 * @param mailSubject	邮件主题
	 * @param mailText		邮件内容
	 * @param sessionDebug	是否在控制打印控制信息
	 * 
	 */
	public static void send(String smtpHost,String addresser,String addresserPwd,String mailFrom,
			String[] addressees,String[] carbonCopyStrs,String mailSubject,String mailText,boolean sessionDebug){
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props);
	    session.setDebug(sessionDebug);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(mailFrom);
	        //数组转换字符串,收件人
	        if(null != addressees){
	        	String toList = getMailList(addressees); 
	        	InternetAddress[] adds = InternetAddress.parse(toList);
	        	msg.setRecipients(Message.RecipientType.TO,adds);
	        }
	        //数组转换字符串,抄送
	        if(null != carbonCopyStrs&&carbonCopyStrs.length>0){
	        	String ccList = getMailList(carbonCopyStrs); 
	            InternetAddress[] ccs = InternetAddress.parse(ccList);
		        msg.setRecipients(Message.RecipientType.CC,ccs);
	        }
	        msg.setSubject(mailSubject);
	        msg.setSentDate(new Date());
	        msg.setText(mailText);
	        Transport.send(msg,addresser,addresserPwd);
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}
	
	public static void sendHTML(String smtpHost,String addresser,String addresserPwd,String mailFrom,
			String[] addressees,String[] carbonCopyStrs,String mailSubject,String mailText,boolean sessionDebug){
		
		Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props);
	    session.setDebug(sessionDebug);

	    try {
	    	Message mailMessage = new MimeMessage(session);//根据session创建一个邮件消息
	    	 //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();  
	    	MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含HTML内容的MimeBodyPart  
	    	messageBodyPart.setContent(mailText,"text/html; charset=utf-8");  
	    	
	    	Address from = new InternetAddress(mailFrom);//创建邮件发送者地址  
	    	mailMessage.setFrom(from); //设置邮件消息的发送者
	    	
//			Address to = new InternetAddress(mailInfo.getToAddress());// 创建邮件的接收者地址
//			mailMessage.setRecipient(Message.RecipientType.TO, to);// 设置邮件消息的接收者
			
			mailMessage.setSubject(mailSubject);// 设置邮件消息的主题
			mailMessage.setSentDate(new Date());// 设置邮件消息发送的时间
	      
	        //数组转换字符串,收件人
	        if(null != addressees){
	        	String toList = getMailList(addressees); 
	        	InternetAddress[] adds = InternetAddress.parse(toList);
	        	mailMessage.setRecipients(Message.RecipientType.TO,adds);
	        }
	        //数组转换字符串,抄送
	        if(null != carbonCopyStrs&&carbonCopyStrs.length>0){
	        	String ccList = getMailList(carbonCopyStrs); 
	            InternetAddress[] ccs = InternetAddress.parse(ccList);
	            mailMessage.setRecipients(Message.RecipientType.CC,ccs);
	        }
	        mainPart.addBodyPart(messageBodyPart);
	        //将MimeMultipart对象设置为邮件内容     
            mailMessage.setContent(mainPart);
	        Transport.send(mailMessage,addresser,addresserPwd);
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}

	/**
	 * 将收件人邮箱数组转成字符串
	 * @param mailArray
	 * @return
	 */
	private static String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (null != mailArray) {
			if (length < 2) {
				toList.append(mailArray[0]);
			} else {
				for (int i = 0; i < length; i++) {
					toList.append(mailArray[i]);
					if (i != (length - 1)) {
						toList.append(",");
					}
				}
			}
		}
		return toList.toString();

	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}

	public void setAddresserPwd(String addresserPwd) {
		this.addresserPwd = addresserPwd;
	}

}
