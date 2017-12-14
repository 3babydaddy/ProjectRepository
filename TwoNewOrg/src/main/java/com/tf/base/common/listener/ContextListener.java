package com.tf.base.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tf.base.common.constants.CommonConstants;
import com.tf.base.common.utils.ApplicationProperties;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getServletContext().getRealPath("/") + "===================>");
		CommonConstants.ROOT_DIR = arg0.getServletContext().getRealPath("/");
	}

}
