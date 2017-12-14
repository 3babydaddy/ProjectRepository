package com.tf.base.common.utils;

/**
 * 发送Email的Task.
 * 
 */
public class SendEmailTask implements Runnable {

	/**
	 * 发信服务器.
	 */
	private String mailServer = null;

	/**
	 * 认证用户名.
	 */
	private String mailAuthUser = null;

	/**
	 * 认证密码.
	 */
	private String mailAuthPw = null;

	/**
	 * 发信人.
	 */
	private String mailFrom = null;

	/**
	 * 收信人.
	 */
	private String[] mailTo = null;
	/**
	 * 抄送人.
	 */
	private String[] mailCopyTo = null;

	/**
	 * 主题.
	 */
	private String subject = null;

	/**
	 * 内容
	 */
	private String content = null;

	public SendEmailTask(String mailServer, String mailAuthUser,
			String mailAuthPw, String mailFrom, String[] mailTo,String [] mailCopyTo,
			String subject, String content) {
		this.mailServer = mailServer;
		this.mailAuthUser = mailAuthUser;
		this.mailAuthPw = mailAuthPw;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
		this.mailCopyTo=mailCopyTo;
	}
	public SendEmailTask(String mailServer, String mailAuthUser,
			String mailAuthPw, String mailFrom, String[] mailTo,
			String subject, String content) {
		this.mailServer = mailServer;
		this.mailAuthUser = mailAuthUser;
		this.mailAuthPw = mailAuthPw;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
	}
	/**
	 * 执行.
	 */
	public void run() {
		MailSenderUtil.sendHTML(mailServer, mailAuthUser, mailAuthPw, mailFrom,
				mailTo, mailCopyTo, subject, content, true);
	}

}
