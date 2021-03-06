package com.tf.permission.client.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tf.permission.client.entity.User;

@Service
public class PermissionClientBaseService {

	private static ThreadLocal<Map<String, Object>> userBaseInfo = new InheritableThreadLocal<Map<String,Object>>(); 
	
	public void setBaseInfo(String ip,String mac,User user) {
		
		userBaseInfo.remove();
		
		Map<String, Object> newBaseInfo = new HashMap<String, Object>();
		
		newBaseInfo.put("ip" , ip);
		newBaseInfo.put("mac" , mac);
		newBaseInfo.put("user" , user);
		
		userBaseInfo.set(newBaseInfo);
	}
	
	public Object getBaseInfo(String key) {
		
		return userBaseInfo.get().get(key);
	}
	
	public String getUserName() {
		
		return ((User)this.getBaseInfo("user")).getUsername();
	}
	
	public String getShowName() {
		
		return ((User)this.getBaseInfo("user")).getShowname();
	}
	
	public String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date now = new Date(); 
		
		return df.format( now );
	}
	
	public String parseDateFormat(String inputFormat, String outputFormat, String dateStringToParse){
		
		if ("".equals(dateStringToParse) || dateStringToParse == null) {
			return "";
		}
		
		SimpleDateFormat bartDateFormat =  new SimpleDateFormat(inputFormat);  
		
		Date date = null;
		try {
			date = bartDateFormat.parse(dateStringToParse);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		SimpleDateFormat dateFormat =  new SimpleDateFormat(outputFormat);  
		
		return dateFormat.format(date);
	}
}
