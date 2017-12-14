package com.tf.base.common.service;


import java.util.Date;

import com.tf.permission.client.entity.LogInfo;
import com.tf.permission.client.service.PermissionClientService;

public class LogTask implements Runnable {

	private String systemid;
	private String username;
	private String type;
	private String desc;
	private Date date;
	private String ip;
	
	private PermissionClientService permissionClientService;
	
	public LogTask(String systemid, String username, String type, String desc,
			Date date,String ip,
			PermissionClientService permissionClientService) {
		super();
		this.systemid = systemid;
		this.username = username;
		this.type = type;
		this.desc = desc;
		this.date = date;
		this.ip = ip;
		this.permissionClientService = permissionClientService;
	}




	@Override
	public void run() {
		
		LogInfo log = new LogInfo();
		log.setSystemid(systemid);
		log.setUsername(username);
		log.setType(type);
		log.setDesc(desc);
		log.setCreatetime(date);
		log.setIp(ip);
		try {
			
			permissionClientService.saveLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
}
