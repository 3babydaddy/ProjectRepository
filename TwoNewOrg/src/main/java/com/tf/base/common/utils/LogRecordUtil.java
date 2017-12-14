package com.tf.base.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.domain.DictionaryRepository;

public class LogRecordUtil {

public static String getAddOrModify(int saveOrUpdate, DictionaryRepository dict, Object oldobj,Object newobj) {
		
		List<String> logs = new ArrayList<String>();
	    String log = null;
		try {
				log = getLogShow(saveOrUpdate,dict,newobj, oldobj);
		}catch (Exception e) {
				
		}
		if (log != null) {
				logs.add(log);
		}
		if (logs.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String item : logs) {
			sb.append(item).append("、");
		}
		return sb.substring(0, sb.length() -1);
	}

	private static String getLogShow(int saveOrUpdate, DictionaryRepository dict, Object o,Object oldo) throws Exception {

		Field[] fields = o.getClass().getDeclaredFields();
		
		List<String> logs = new ArrayList<String>();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(LogShowName.class)) {
				String value = field.getAnnotation(LogShowName.class).value();
				String   dmm = field.getAnnotation(LogShowName.class).dmm();
				if(oldo !=null){
					logs.add(getShow(saveOrUpdate, dict ,value, dmm, field.get(o),field.get(oldo)));
				}else{
					logs.add(getShow(saveOrUpdate, dict ,value, dmm, field.get(o),null));
				}
				
			}
		}
		if (logs.size() == 0) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String item : logs) {
			if(!StringUtil.isEmpty(item))
			sb.append(item).append("、");
		}
		return sb.length()>1?sb.substring(0, sb.length() -1):sb.toString();
	}

	private static String getShow(int saveOrUpdate, DictionaryRepository dict, String value, String dmm, Object object,Object oldobj) {
		if(isObjectEqual(object,oldobj)){
			return "";
		}
		StringBuilder sb = new StringBuilder(saveOrUpdate==1?"新增“":"更新“");
		if (object == null) {
			sb.append(value).append(saveOrUpdate==0?"原:"+(oldobj==null?"为“空”":oldobj)+"现:":"").append("”为“空”");
		}else {
			
			if (object instanceof String) {
				if (StringUtil.isEmpty((String)object)) {
					boolean oldobjnull=false;
					if(oldobj==null||StringUtil.isEmpty(String.valueOf(oldobj))){
						oldobjnull=true;
						return "";
					}
					sb.append(value).append(saveOrUpdate==0?"原:"+(oldobjnull?"为“空”":oldobj)+"现:":"").append("”为“空”");
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
				boolean oldobjnull=false;
				if(oldobj==null||StringUtil.isEmpty(String.valueOf(oldobj))){
					oldobjnull=true;
				}
				sb.append(value).append("”为“").append(saveOrUpdate==0?"原:"+(oldobjnull?"为“空”":oldobj)+
						                                              "现:":"").append(object).append("”");
			} else {
				sb.append(value).append("”为“").append(saveOrUpdate==0?"原:"+
						 dict.getValueByCode(dmm, oldobj.toString())+"现:":"").append(dict.getValueByCode(dmm, object.toString())).append("”");
			}
		}
		return sb.toString();
	}
	
	private static boolean isObjectEqual(Object one, Object two) {
		if (one == null && two == null) {
			return true;
		}
		if (one == null || two == null) {
			return false;
		}
		return one.equals(two);
	}
}
