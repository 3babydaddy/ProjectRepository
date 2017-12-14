/**
 * 
 */
package com.tf.base.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.tf.base.common.constants.CommonConstants;

/**
 * @author spring
 *
 */
public class PageUtil {
	
	/**
	 * 返回分页数据给grid列表
	 * @param response
	 * @param page
	 */
	public static <T> void returnPage(HttpServletResponse response,PageInfo<T> page){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("total", page.getTotal());
		result.put("rows", page.getList());
		JSONUtil.toJson(response, CommonConstants.CONTENT_TYPE, result);
	}
}
