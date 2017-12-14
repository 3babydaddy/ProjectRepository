/**
 * 
 */
package com.tf.base.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tf.base.common.constants.CommonConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JSONUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 多传JSON字段过来不要报错，不设置会报错的。
	 */
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	/**
	 * 将对象转换为JSON字符串
	 * @param object 对象
	 */
	public static String toJson(Object object) {
		Assert.notNull(object);
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将对象转换为JSON流
	 * @param response HttpServletResponse
	 * @param contentType contentType
	 * @param object 对象
	 */
	public static void toJson(HttpServletResponse response, String contentType, Object value) {
		Assert.notNull(response);
		Assert.notNull(contentType);
		Assert.notNull(value);
		try {
			response.setCharacterEncoding(CommonConstants.CHARACTER_ENCODE);
			response.setContentType(contentType);
			mapper.writeValue(response.getWriter(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将对象转换为JSON流
	 * @param response HttpServletResponse
	 * @param object 对象
	 */
	public static void toJson(HttpServletResponse response, Object value) {
		Assert.notNull(response);
		Assert.notNull(value);
		try {
			mapper.writeValue(response.getWriter(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将JSON字符串转换为对象
	 * @param json JSON字符串
	 * @param valueType 对象类型
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.notNull(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将JSON字符串转换为对象
	 * @param json JSON字符串
	 * @param typeReference 对象类型
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.notNull(json);
		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将JSON字符串转换为对象
	 * @param json JSON字符串
	 * @param javaType 对象类型
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.notNull(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将JSON字符串转换成JSON对象
	 * @param json
	 * @return
	 */
	public static JSONObject toObject(String json){
		return JSONObject.fromObject(json);
	}
	
	/**
	 * 将JSON字符串转换成JSON对象数组
	 * @param json
	 * @return
	 */
	public static JSONArray toJSONArray(String json){
		return JSONArray.fromObject(json);
	}
	
	/**
	 * JSON字符串格式化
	 * @param json
	 * @return
	 */
	public static String jsonFormat(String json){
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(json);
			return gson.toJson(je);
		}catch(Exception e){		
			e.printStackTrace();		
		}
		
		return null;
		
	}
	
	public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');//
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    
    public static String formatJsonToHtml(String jsonStr){
    	
    	String formatJson =  formatJson(jsonStr) ;
    	formatJson = formatJson.replaceAll("\r\n", "<br>");//需要分开处理
    	formatJson = formatJson.replaceAll("\r", "<br>");
    	formatJson = formatJson.replaceAll("\n", "<br>");
    	return formatJson ;
    			
    }
    
    /**
     * 将实体POJO转化为JSON
     * @param obj
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static<T> JSONObject objectToJson(T obj) throws JSONException, IOException {
	    ObjectMapper mapper = new ObjectMapper(); 
	    // Convert object to JSON string 
	    String jsonStr = "";
	    try {
	       jsonStr = mapper.writeValueAsString(obj);
	    } catch (IOException e) {
	      throw e;
	    }
	    JSONObject obj1 = new JSONObject();
	    obj1= JSONObject.fromObject(jsonStr);
	    return obj1 ;
	  }
}
