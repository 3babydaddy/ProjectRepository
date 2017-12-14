package com.tf.base.common.utils;

import java.lang.reflect.Field;

import com.tf.base.common.annotation.DataValidator;
public class DataValidatorUtil {
	
	/**
	 * 获取要验证类的指定字段是否填写了数据
	 * 后续扩展正则表达式来判断输入数据是否满足格式要求
	 * @param o
	 * @return
	 */
	public static String getValidatorInfo(Object o){
		
		StringBuffer sbf = new StringBuffer();
        Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(DataValidator.class)) {
				String value = field.getAnnotation(DataValidator.class).value(); //获取注解字段名称
				Object objv  = null ;
				try {
					objv = field.get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if(objv instanceof String){
					if(objv == null || (objv!=null && "".equals(objv.toString().trim()) )){
						sbf.append(value).append("、");
					}
				}else{
					if(objv == null){
						sbf.append(value).append("、");
					}
				}
			}
		}
		return sbf.toString();
	}

}
