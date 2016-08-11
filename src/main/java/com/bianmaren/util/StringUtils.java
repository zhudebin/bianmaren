package com.bianmaren.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class StringUtils {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 转十六进制
	 * 
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	/**
	 * 整数转十六进制
	 * @param bytes
	 * @return
	 */
	public static String int2Hex(int integer) {
		StringBuffer buf = new StringBuffer(2); 
        if (((int) integer & 0xff) < 0x10) { 
            buf.append("0"); 
        } 
        buf.append(Long.toString((int) integer & 0xff, 16)); 
    return buf.toString(); 
	}
	
	/**
	 * 通过手机号码判断运营商
	 * @param mobile
	 * @return
	 */
	public static String checkSP(String mobile){
		if(mobile==null) return "未知";
		
		List<String> uncom=Arrays.asList("130", "131", "132", "145","155", "156", "185","186");
		List<String> mobcom1=Arrays.asList("135", "136", "137", "138", "139", "147","150", "151", "152", "157", "158", "159", "182", "183","187","188");
		List<String> mobcom2=Arrays.asList("1340", "1341", "1342", "1343", "1344", "1345", "1346", "1347", "1348");
		List<String> telecom=Arrays.asList("133","1349","153","180","181","189");
		List<String> vircom=Arrays.asList("170");
		
		mobile=mobile.replace("+", "");
		if(mobile.startsWith("86")) mobile=mobile.substring(2);
		if(mobile.startsWith("0")) mobile=mobile.substring(1);
		if(mobile.length()==11){
			boolean isUncom=uncom.contains(mobile.substring(0, 3));
			boolean isMobcom1=mobcom1.contains(mobile.substring(0, 3));
			boolean isMobcom2=mobcom2.contains(mobile.substring(0,4));
			boolean isTelecom=telecom.contains(mobile.substring(0, 3));
			boolean isVircom=vircom.contains(mobile.substring(0, 3));
			
			if(isUncom) return "联通";
			if(isMobcom1||isMobcom2) return "移动";
			if(isTelecom) return "电信";
			if(isVircom) return "虚拟";
		}else{
			return "号码段长度不符合手机号码长度";
		}
		return null;
	}
	
	public static String toString(Collection<String> list,String divide){
		if(list==null||list.size()==0||divide==null){
			return null;
		}
		String str = "";
		for(Object obj : list){
			str += obj.toString() + divide;
		}
		str = str.substring(0,str.length()-1);
		return str;
	}
	
	/**
	 * 
	 * @Description: 随机字符串
	 * @author 441889070@qq.com
	 * @date 2015年11月12日 下午1:55:21
	 * @param
	 */
	 public static String randomString(int length)
	 {
		  String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		  Random  random = new Random();
		  StringBuffer buf = new StringBuffer();
		  for(int i = 0 ;i < length ; i ++)
		  {
			   int num = random.nextInt(62);
			   buf.append(str.charAt(num));
		  }
		  return buf.toString();
	 }
	 
	 /**
	  * 
	  * @Description: 随机数字类型字符串
	  * @author 441889070@qq.com
	  * @date 2015年11月12日 下午1:55:31
	  * @param
	  */
	 public static String randomNumber(int length)
	 {
		  String str="0123456789";
		  Random  random = new Random();
		  StringBuffer buf = new StringBuffer();
		  for(int i = 0 ;i < length ; i ++)
		  {
			   int num = random.nextInt(9);
			   buf.append(str.charAt(num));
		  }
		  return buf.toString();
	 }
	 
	 public static String coalesce(String... args){
    	for (int i = 0; i < args.length; i++) {
 		   if(null != args[i] && !"".equals(args[i])){
 			   return args[i];
 		   }
 		}
    	return "";
     }
	 
	 public static boolean vaildeParam(String... args){
    	for (int i = 0; i < args.length; i++) {
 		   if(null == args[i] || "".equals(args[i])){
 			   return false;
 		   }
 		}
    	return true;
     }
}
