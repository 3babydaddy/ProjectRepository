package com.tf.base.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tf.base.common.domain.DataDictionary;
import com.tf.base.common.domain.DictionaryRepository;

public class GeneralCalcUtils {

	
	/**
	 * 由出生日期获得年龄  
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
    public static  int getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }
    
    /**
	 * 多级select转换
	 * @param dataDictionaries
	 * @return
	 */
	public static  Map<String,List<DataDictionary>> convertMap(List<DataDictionary> dataDictionaries){
		Map<String,List<DataDictionary>> map = new LinkedHashMap<>();
		Map<String,List<DataDictionary>> lastmap = new LinkedHashMap<>();
		
		Map<String,String> keyMappings = new LinkedHashMap<>();
		for (DataDictionary dataDictionary : dataDictionaries) {
			if (null == dataDictionary.getLevel()) {
				List<DataDictionary> tmp = new ArrayList<DataDictionary>();
				tmp.add(dataDictionary);
				lastmap.put(dataDictionary.getValue(), tmp);
				return lastmap;
			} else if (dataDictionary.getLevel().toString().equals("1")) {
				map.put(dataDictionary.getCode(), null);
				keyMappings.put(dataDictionary.getCode(), dataDictionary.getValue());
			}
		}
		for (String key : map.keySet()) {  
			for (DataDictionary dataDictionary : dataDictionaries) {
				List<DataDictionary> tmp = map.get(key);
				if(dataDictionary.getLevel().toString().equals("2") 
						&& dataDictionary.getCode().startsWith(key)){
					if(tmp == null){
						tmp = new ArrayList<>();
						tmp.add(dataDictionary);
						map.put(key, tmp);
					}else{
						tmp.add(dataDictionary);
						map.put(key, tmp);
					}
				}
			}
		}  
		
		for (String key : keyMappings.keySet()) {  
			String value = keyMappings.get(key);
			List<DataDictionary> list = map.get(key);
			lastmap.put(value, list);
		}  
		return lastmap;
	}
	
	public static String splitConvertField(DictionaryRepository dict,String code, String value) {
		StringBuffer sb = new StringBuffer();
		
		if(value == null || value.equals(""))
			return "";
		String values[] = value.split(",");
		if(values.length > 0){
			
			for (String v : values) {
				sb.append(dict.getValueByCode(code, v));
				sb.append(",");
			}
			return (!sb.toString().equals("") ? sb.toString().substring(0,sb.toString().length()-1) : sb.toString());
		}else{
			return "";
		}
	}
}
