package com.bianmaren.util;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
/**
 * 解析模块配置文件
 * @author john
 *
 */
public final class PropertiesUtils {
	
	private static final Properties properties = new Properties();
	static{
		try {
			properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("config/diange_setting.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Set<String> getKeys(){
		return properties.stringPropertyNames();
	}
	
	public static String getValue(String key){
		return properties.getProperty(key);
	}
	
}
