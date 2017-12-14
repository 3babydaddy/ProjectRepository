package com.tf.base.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.domain.DictionaryRepository;

public class LogInfoExtUtil {
	
	static Logger logger = LoggerFactory.getLogger(LogInfoExtUtil.class);
	
	public static final String OLD_VALUE = "oldValue";
	public static final String NEW_VALUE = "newValue";
	public static final String FIELD_NAME = "fieldName";
	public static final String LOG_SHOWNAME = "LogShowName";
	
	public static final int LOG_METHOD_ADD = 1;
	public static final int LOG_METHOD_MODIFY = 2;
	
	
	public static String getAddLog(DictionaryRepository dict, Object newObj){
		
		logger.debug("LogInfoExtUtil : ==================>调用log日志[新增]方法.");
		return getModify(LOG_METHOD_ADD, dict, null, newObj);
	}
	
	public static String getModifyLog(DictionaryRepository dict, Object oldObj,Object newObj) {
		logger.debug("LogInfoExtUtil : ==================>调用log日志[修改]方法.");
		return getModify(LOG_METHOD_MODIFY, dict, oldObj, newObj);
		
	}

	private static String getModify(int addOrUpdate,DictionaryRepository dict, Object oldObj,Object newObj) {
		
		List<String> logs = new ArrayList<String>();
		
		List<Map<String, Object>> logInfoList = new ArrayList<>();
		if(LOG_METHOD_ADD == addOrUpdate)
			logInfoList = getClassFieldAndValue(dict, newObj);
		else
			logInfoList = compareTwoClass(dict, oldObj, newObj);
		
		String log = null;
		try {
			log = getLogShow(addOrUpdate,dict, logInfoList);
		} catch (Exception e) {
			
			e.printStackTrace();
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
	
	

	private static String getLogShow(int addOrUpdate, DictionaryRepository dict,List<Map<String, Object>> maps) throws Exception {

		
		List<String> logs = new ArrayList<String>();
		for (Map<String, Object> map : maps) {
			if(LOG_METHOD_ADD == addOrUpdate)
				logs.add(getAddShow(dict, map.get(LOG_SHOWNAME), map.get(NEW_VALUE)));
			else
				logs.add(getModifyShow(dict, map.get(LOG_SHOWNAME),map.get(OLD_VALUE), map.get(NEW_VALUE)));
		}
		
		
		if (logs.size() == 0) {
			
			if(LOG_METHOD_MODIFY == addOrUpdate)
				return "原记录未做变动.";
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String item : logs) {
			
			//sb.append(item).append("、");
			sb.append(item);
		}
		
		
		return sb.substring(0, sb.length() -1);
	}
	

	private static String getAddShow(DictionaryRepository dict, Object logShowName,  Object newObject) {

//		StringBuilder sb = new StringBuilder("【新增】:");
		StringBuilder sb = new StringBuilder();
		if (newObject == null) {
			sb.append("[").append(logShowName).append("]为“空”;");
		} else {
			
			if (newObject instanceof String) {
				
				if (StringUtil.isEmpty((String)newObject)) {
					sb.append("[").append(logShowName).append("]为“空”;");
					
					return sb.toString();
				}
			}
			
			sb.append("[").append(logShowName).append("]为“").append(newObject).append("”;");
		}
		
		return sb.toString();
	}
	
	private static String getModifyShow(DictionaryRepository dict, Object logShowName,  Object oldObject,Object newObject) {

//		StringBuilder sb = new StringBuilder("【更新】:");
		StringBuilder sb = new StringBuilder();
		if (newObject == null) {
			if(oldObject == null)
				sb.append(logShowName).append("[原始]为“空”,[现在]为“空”;");
			else
				sb.append(logShowName).append("[原始]”为“" +oldObject+ "”,[现在]为“空”;");
		} else {
			
			if (newObject instanceof String) {
				
				if (StringUtil.isEmpty((String)newObject)) {
					if(oldObject == null)
						sb.append(logShowName).append("[原始]为“空”,[现在]为“" +newObject+ "”;");
					else
						sb.append(logShowName).append("[原始]为“"+oldObject+"”,[现在]为“空”;");
					
					return sb.toString();
				}
			}
			if(newObject instanceof Date){
				String old = DateUtil.DateToString((Date)oldObject);
				String n = DateUtil.DateToString((Date)newObject);
					sb.append(logShowName).append("[原始]为“"+old+"”,[现在]为“" +n+ "”;");
					
					return sb.toString();
			}
			
			sb.append(logShowName).append("[原始]为“").append(oldObject).append("”,[现在]为“").append(newObject).append("”;");
		}
		
		/*if(sb.length() == 0){
			return  "原记录未做变动.";
		}*/
		
		return sb.toString();
	}
	
	private static List<Map<String, Object>> getClassFieldAndValue(DictionaryRepository dict,Object class1) {
		List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();  
		Class<?> clazz1 = class1.getClass(); 
		
		Field[] fields1 = clazz1.getDeclaredFields();
		for (Field field1 : fields1) {
			field1.setAccessible(true);  
			
			if (field1.isAnnotationPresent(LogShowName.class)) {
				 LogShowName  log = field1.getAnnotation(LogShowName.class);
				 if(log == null){
		        		continue;
				 }
				 String value = log.value().toString();
	        	 String dmm = log.dmm() ;
	        	 Map<String,Object> map2=new HashMap<String, Object>();  
				   map2.put(FIELD_NAME,field1.getName());  
				   map2.put(LOG_SHOWNAME,value);
				  
				   try {
					   if(!StringUtil.isEmpty(log.dmm())){
						   map2.put(NEW_VALUE,dict.getValueByCode(dmm, (String)field1.get(class1)));  
					   }else{
						   map2.put(NEW_VALUE,field1.get(class1));
					   }
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}  
				   list.add(map2);  
			}
		}
		return list;
	}
	
	private static List<Map<String, Object>> compareTwoClass(DictionaryRepository dict,Object class1, Object class2) {
		List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();  
	    Class<?> clazz1 = class1.getClass();  
	    Class<?> clazz2 = class2.getClass();  
	    
	    Field[] fields1 = clazz1.getDeclaredFields();  
	    Field[] fields2 = clazz2.getDeclaredFields();  
	    for (Field field1 : fields1) {
			for (Field field2 : fields2) {
		         if(field1.getName().equals(field2.getName())){  
		             if(field1.getName().equals(field2.getName())){  
		                field1.setAccessible(true);  
		                field2.setAccessible(true);  
		                
		                if (!field1.isAnnotationPresent(LogShowName.class)) {
		                	continue;
		                }
		                
		                LogShowName  log = field1.getAnnotation(LogShowName.class);
			        	if(log == null){
			        		continue;
			        	}
			        	String value = log.value().toString();
			        	String dmm = log.dmm() ;
			        	
		                //如果field1[i]属性值与field2[j]属性值内容不相同  
		                try {
							if (!compareTwo(field1.get(class1), field2.get(class2))){  
							   Map<String,Object> map2=new HashMap<String, Object>();  
							   map2.put(FIELD_NAME,field1.getName());  
							   map2.put(LOG_SHOWNAME,value);
							  
							   if(!StringUtil.isEmpty(log.dmm())){
								   map2.put(OLD_VALUE,dict.getValueByCode(dmm, (String)field1.get(class1)));  
								   map2.put(NEW_VALUE,dict.getValueByCode(dmm, (String)field2.get(class2)));  
							   }else{
								   map2.put(OLD_VALUE,field1.get(class1));  
								   map2.put(NEW_VALUE,field2.get(class2));  
							   }
							   
							   list.add(map2);  
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}  
		               break;  
		         }  
		        } 
			}
		}
		return list;
	}
	
	private static boolean compareTwo(Object object1,Object object2){  
		  
	    if(object1==null&&object2==null){  
	        return true;  
	    }  
	    if(object1==null&&object2!=null){  
	        return false;  
	    }  
	    if(object1.equals(object2)){  
	        return true;  
	    }  
	    return false;  
	} 
}
