package com.tf.base.common.service;


import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.tf.permission.client.constants.PermissionConstants;
import com.tf.permission.client.entity.User;
import com.tf.permission.client.service.PermissionClientService;

@Service
public class LogService {
	
	@Autowired
	private PermissionClientService permissionClientService;
	
	@Autowired
	private BaseService baseService;
	
	/**
     * 信息源接口
     */
    @Autowired
    private MessageSource messageSource;
	
	@Value("${permissionClient.systemid}")
	private String systemid ;

	public String getDetailInfo(String code, Object... args) {
		
		return messageSource.getMessage(code, args, null);
	}


	public int saveLog(String type,String desc){
		
		LogTask logTask = new LogTask(systemid,baseService.getUserName(),
				type,desc,new Date(),
				baseService.getBaseInfo("ip").toString(),
				permissionClientService);
		LogCenter.addLogTask(logTask);
		
		return 1;
	}

}
