package com.tf.base.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 负责发送Email. 使用线程池，等待任务来临.
 * 
 */
public class MailCenter {

	/**
	 * 线程池_发信.
	 */
	private static ExecutorService emailExecutor = Executors
			.newFixedThreadPool(4);

	/**
	 * 增加发送Email的任务.
	 * 
	 * @param command
	 *            任务
	 */
	public static void addSendEmailTask(Runnable command) {
		emailExecutor.execute(command);
	}

	/**
	 * 发信.
	 * 
	 * @param mailTo
	 *            收件人
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 */
	public static void sendEmail(String mailTo, String subject, String content) {
		
		//TODO:参数
		String mailServer = "";
		String mailAuthUser = "";
		String mailAuthPw = "";
		String mailFrom = "";
		String[] mailToArray = new String[] { mailTo };
		SendEmailTask task = new SendEmailTask(mailServer, mailAuthUser,
				mailAuthPw, mailFrom, mailToArray, subject, content);
		addSendEmailTask(task);
	}
	/**
	 * 发信.
	 * 
	 * @param mailTo
	 *            收件人
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 */
	public static void sendEmail(String [] mailTo, String subject, String content,String mailServer,String mailAuthUser,String mailAuthPw,String mailFrom) {
		
		SendEmailTask task = new SendEmailTask(mailServer, mailAuthUser,
				mailAuthPw, mailFrom, mailTo, subject, content);
		addSendEmailTask(task);
	}
	/**
	 * 发信.
	 * 
	 * @param mailTo
	 *            收件人
	 * @param mailTo
	 *            抄送人
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 */
	public static void sendEmail(String [] mailTo, String [] mailCopyTo,String subject, String content,String mailServer,String mailAuthUser,String mailAuthPw,String mailFrom) {
		
		SendEmailTask task = new SendEmailTask(mailServer, mailAuthUser,
				mailAuthPw, mailFrom, mailTo, mailCopyTo,subject, content);
		addSendEmailTask(task);
	}
}
