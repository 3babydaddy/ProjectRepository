package com.tf.base.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	/**
	 * 返回信息及标示
	 * @param status
	 * @param msg
	 * @return
	 */
	public Map<String,Object> returnMsg(int status,String msg){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}
	
	
}
