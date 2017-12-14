package com.tf.base.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.domain.DictionaryRepository;

public class LogInfoUtil {

	public static String getModify(DictionaryRepository dict, Object... obj) {
		
		List<String> logs = new ArrayList<String>();
		
		for (Object o : obj) {
			
			String log = null;
			try {
				log = getLogShow(dict, o);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (log != null) {
				logs.add(log);
			}
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

	private static String getLogShow(DictionaryRepository dict,Object o) throws Exception {

		Field[] fields = o.getClass().getDeclaredFields();
		
		List<String> logs = new ArrayList<String>();
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			if (field.isAnnotationPresent(LogShowName.class)) {
				String value = field.getAnnotation(LogShowName.class).value();
				
				String dmm = field.getAnnotation(LogShowName.class).dmm();
				logs.add(getShow(dict, value, dmm, field.get(o)));
			}
		}
		
		if (logs.size() == 0) {
			
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String item : logs) {
			
			sb.append(item).append("、");
		}
		
		
		return sb.substring(0, sb.length() -1);
	}

	private static String getShow(DictionaryRepository dict, String value, String dmm, Object object) {

		StringBuilder sb = new StringBuilder("更新“");
		if (object == null) {
			sb.append(value).append("”为“空”");
		} else {
			
			if (object instanceof String) {
				
				if (StringUtil.isEmpty((String)object)) {
					sb.append(value).append("”为“空”");
					
					return sb.toString();
				}
			}
			
			if ("".equals(dmm)) {
				sb.append(value).append("”为“").append(object).append("”");
			} else {
				
				sb.append(value).append("”为“").append(dict.getValueByCode(dmm, (String)object)).append("”");
			}
		}
		
		return sb.toString();
	}
}
