package com.tf.base.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.domain.DictionaryRepository;
public class LogUtil {

	
	/**
	 * 将Bean数据，根据注解将字段属性名转成注解名
	 * @return
	 */
	public static String  getLogInfoFromBigBean(DictionaryRepository dict ,Object object){
		
		try{
			StringBuffer sbf = new StringBuffer(500);
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				
				field.setAccessible(true);
				String filedType = field.getType().toString() ;
				if(field.get(object) !=null){
					sbf.append("  " + field.getName() + "  " );
					if(filedType.contains("java.util.List")){
						List<Object> oblst =  (List<Object>) field.get(object);
						for(Object obj : oblst){
							if(obj !=null){
								String logs  =  getLogShow(null,obj, false);
								if(!StringUtil.isEmpty(logs)){
									sbf.append(logs);
								}
							}
						}
					}else if(filedType.contains("com.trip")){
						String logs = getLogShow(null,field.get(object),false);
						if(!StringUtil.isEmpty(logs)){
							sbf.append("   " +logs);
						}
					}
				}
			}
			return sbf.toString();
		}catch(Exception e){
			return "";
		}
	}
	
	public static String getLogInfo(String logType, DictionaryRepository dict, Object... obj ) {
		
		StringBuilder sb = new StringBuilder();
		for (Object o : obj) {
			
			String log = null;
			try {
				log = getLogShow(dict, o , true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (log != null) {
				sb.append(log).append("、");
			}
		}
		if (sb.toString().length() == 0) {
			return "";
		}
		return logType + sb.substring(0, sb.length() -1);
	}
    /**
     * 获取一个类的Log信息
     * @param dict
     * @param o
     * @param isNullShowLog
     * @return
     */
	private static String getLogShow(DictionaryRepository dict,Object o,boolean isNullShowLog){

		StringBuilder sb = new StringBuilder();
		try{
			
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				
				field.setAccessible(true);
				if (field.isAnnotationPresent(LogShowName.class)) {
					String value = field.getAnnotation(LogShowName.class).value();
					String dmm   = field.getAnnotation(LogShowName.class).dmm();
					//当标记数据为空时或者数值不为空 才增加log  
					if(isNullShowLog || field.get(o) !=null ){
						sb.append(getShow(dict, value, dmm, field.get(o))).append("、");
					}
				}
			}
		}catch(Exception e){
			return null ;
		}
		if (sb.toString().length() == 0) {
			return null;
		}
		return sb.substring(0, sb.length() -1);
	}
    /**
     * 获取一个属性的Log名称和属性值
     * @param dict
     * @param value
     * @param dmm
     * @param object
     * @return
     */
	private static String getShow(DictionaryRepository dict, String value, String dmm, Object object) {

		StringBuilder sb = new StringBuilder();
		if (object == null) {
			sb.append(value).append("为空");
		}else{
			if (object instanceof String) {
				if (StringUtil.isEmpty((String)object)) {
					sb.append(value).append("为空");
					return sb.toString();
				}
			}
			if (object instanceof String[]) {
				if(object !=null ){
					String str = "";
					for(String s : (String[])object){
						str = str + s +":" ;
					}
					sb.append(value).append(str);
					return sb.toString();
				}
			}
			if (StringUtil.isEmpty(dmm)) {
				sb.append(value).append("为").append(object).append("|");
			} else {
				if(dict !=null){
					sb.append(value).append("为")
					  .append(dict.getValueByCode(dmm,(String)object)).append("|");
				}
			}
		}
		return sb.toString();
	}
	
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception{
		
	}
	
}
