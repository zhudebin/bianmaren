
package com.bianmaren.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getOk 
	 * @author dengwenbing 
	 * @Description: 返回成功
	 *
	* @return
	 */
	public static String getOk() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("result", map);
		try {
			return JSON.toJSONString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getFalse 
	 * @author dengwenbing 
	 * @Description: 返回失败
	 *
	 * @param code
	 * @return
	 */
	public static String getFalse(int code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", String.valueOf(code));
		map.put("message", "null");
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("result", map);
		try {
			return JSON.toJSONString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getFalse 
	 * @author dengwenbing 
	 * @Description: 返回失败
	 *
	 * @param code
	 * @return
	 */
	public static String getFalse(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", "null");
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("result", map);
		try {
			return JSON.toJSONString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getFalse 
	 * @author dengwenbing 
	 * @Description: 返回失败
	 *
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFalse(String code, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", message);
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("result", map);
		try {
			return JSON.toJSONString(resultmap);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getFalseForWeb 
	 * @author dengwenbing 
	 * @Description: 返回失败
	 *
	 * @param code
	 * @return
	 */
	public static String getFalseForWeb(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", "null");
		try {
			return JSON.toJSONString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getFalseForWeb 
	 * @author dengwenbing 
	 * @Description: 返回失败
	 *
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFalseForWeb(String code,String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "false");
		map.put("code", code);
		map.put("message", message);
		try {
			return JSON.toJSONString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getTrueForWeb 
	 * @author dengwenbing 
	 * @Description: 返回成功
	 *
	 * @return
	 */
	public static String getTrueForWeb() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		try {
			return JSON.toJSONString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getTrueForWeb 
	 * @author dengwenbing 
	 * @Description: 返回成功
	 *
	 * @param i
	 * @return
	 */
	public static String getTrueForWeb(int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true" + i);
		try {
			return JSON.toJSONString(map);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getResultFromMap 
	 * @author dengwenbing 
	 * @Description: 将对象转化成json字符串
	 *
	 * @param result
	 * @return
	 */
	public static String getResultFromMap(Map<String, Object> result) {
		try {
			return JSON.toJSONString(result);
		} catch (Exception e) {
			return null;
		}
	}
}