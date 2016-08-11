
package com.bianmaren.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class WebUtils {

	private WebUtils() { }
	
	/**
	 * 数据保存到Cookie中
	 * @param request
	 * @param response
	 * @param name 名称
	 * @param value 值
	 * @param maxAge 保存时间（秒）
	 * @param path 保存路径
	 * @param domain
	 * @param secure
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 数据保存到Cookie中
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge,String cookiePath,String cookieDomain) {
		addCookie(request, response, name, value, maxAge, cookiePath, cookieDomain, null);
	}

	/**
	 * 取出Cookie
	 * @param request
	 * @param name 名称
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param path
	 * @param domain
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);//保存时间为0 即删除
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String getParameter(String queryString, String encoding, String name) {
		String[] parameterValues = getParameterMap(queryString, encoding).get(name);
		return parameterValues != null && parameterValues.length > 0 ? parameterValues[0] : null;
	}
	
	public static String[] getParameterValues(String queryString, String encoding, String name) {
		return getParameterMap(queryString, encoding).get(name);
	}

	public static Map<String, String[]> getParameterMap(String queryString, String encoding) {
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		Charset charset = Charset.forName(encoding);
		if (StringUtils.isNotEmpty(queryString)) {
			byte[] bytes = queryString.getBytes(charset);
			if (bytes != null && bytes.length > 0) {
				int ix = 0;
				int ox = 0;
				String key = null;
				String value = null;
				while (ix < bytes.length) {
					byte c = bytes[ix++];
					switch ((char) c) {
					case '&':
						value = new String(bytes, 0, ox, charset);
						if (key != null) {
							putMapEntry(parameterMap, key, value);
							key = null;
						}
						ox = 0;
						break;
					case '=':
						if (key == null) {
							key = new String(bytes, 0, ox, charset);
							ox = 0;
						} else {
							bytes[ox++] = c;
						}
						break;
					case '+':
						bytes[ox++] = (byte) ' ';
						break;
					case '%':
						bytes[ox++] = (byte) ((convertHexDigit(bytes[ix++]) << 4) + convertHexDigit(bytes[ix++]));
						break;
					default:
						bytes[ox++] = c;
					}
				}
				if (key != null) {
					value = new String(bytes, 0, ox, charset);
					putMapEntry(parameterMap, key, value);
				}
			}
		}
		return parameterMap;
	}

	private static void putMapEntry(Map<String, String[]> map, String name, String value) {
		String[] newValues = null;
		String[] oldValues = map.get(name);
		if (oldValues == null) {
			newValues = new String[] { value };
		} else {
			newValues = new String[oldValues.length + 1];
			System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
			newValues[oldValues.length] = value;
		}
		map.put(name, newValues);
	}

	private static byte convertHexDigit(byte b) {
		if ((b >= '0') && (b <= '9')) {
			return (byte) (b - '0');
		}
		if ((b >= 'a') && (b <= 'f')) {
			return (byte) (b - 'a' + 10);
		}
		if ((b >= 'A') && (b <= 'F')) {
			return (byte) (b - 'A' + 10);
		}
		throw new IllegalArgumentException();
	}

	public static void responseString(HttpServletResponse rsp,String arg){
		rsp.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw=rsp.getWriter();
			pw.write(arg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null) pw.close();
		}
	}
	
	/**
	 * 获取ip地址
	 * @return
	 */
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip; 
	}
	
	/**
	 * 获取MAC地址
	 * @param ip
	 * @return
	 * @throws Exception 
	 */
	public static String getMac(String ip) throws Exception{
		String line = "";  
	    String macAddress = "";  
	    final String MAC_ADDRESS_PREFIX = "MAC Address = ";  
	    final String LOOPBACK_ADDRESS = "127.0.0.1";  
	    //如果为127.0.0.1,则获取本地MAC地址。  
	    if (LOOPBACK_ADDRESS.equals(ip)) {  
	        InetAddress inetAddress = InetAddress.getLocalHost();  
	        //貌似此方法需要JDK1.6。  
	        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();  
	        //下面代码是把mac地址拼装成String  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < mac.length; i++) {  
	            if (i != 0) {  
	                sb.append("-");  
	            }  
	            //mac[i] & 0xFF 是为了把byte转化为正整数  
	            String s = Integer.toHexString(mac[i] & 0xFF);  
	            sb.append(s.length() == 1 ? 0 + s : s);  
	        }  
	        //把字符串所有小写字母改为大写成为正规的mac地址并返回  
	        macAddress = sb.toString().trim().toUpperCase();  
	        return macAddress;  
	    }  
	    //获取非本地IP的MAC地址  
	    try {  
	        Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
	        InputStreamReader isr = new InputStreamReader(p.getInputStream());  
	        BufferedReader br = new BufferedReader(isr);  
	        while ((line = br.readLine()) != null) {  
	            if (line != null) {  
	                int index = line.indexOf(MAC_ADDRESS_PREFIX);  
	                if (index != -1) {  
	                    macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();  
	                }  
	            }  
	        }  
	        br.close();  
	    } catch (IOException e) {  
	        e.printStackTrace(System.out);  
	    }  
	    return macAddress; 
	}
	
	/**
	 * 删除Html标签
	 * 
	 * @param inputString
	 * @return
	 */
	public static String htmlRemoveTag(String inputString) {
	    if (inputString == null)
	        return null;
	    String htmlStr = inputString; // 含html标签的字符串
	    String textStr = "";
	    java.util.regex.Pattern p_script;
	    java.util.regex.Matcher m_script;
	    java.util.regex.Pattern p_style;
	    java.util.regex.Matcher m_style;
	    java.util.regex.Pattern p_html;
	    java.util.regex.Matcher m_html;
	    try {
	        //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
	        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
	        //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
	        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	        String regEx_snrt="\\s*|\n|\r|\t";//定义空格、回车、换行、制表符的正则表达式
	        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // 过滤script标签
	        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // 过滤style标签
	        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // 过滤html标签
	        p_html =Pattern.compile(regEx_snrt, Pattern.CASE_INSENSITIVE);
	        m_html=p_html.matcher(htmlStr);
	        textStr = m_html.replaceAll("");//去除内容当中的所有空格
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return textStr;// 返回文本字符串
	}
	
	/**
	 * 获取服务器根路径
	 * @param req
	 * @return
	 */
	public static String basePath(HttpServletRequest request){
		String basePath = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + request.getContextPath();
		return basePath;
	}

	/**
	 *
	 * @Title: sendGet
	 * @author dengwenbing
	 * @Description: 向指定URL发送GET方法的请求
	 *
	 * @param url	发送请求的URL
	 * @param param	请求参数，请求参数应该是name1=value1&name2=value2的形式
	 * @return
	 */
	public static String sendGet(String url, String param){
		String result = "";
		BufferedReader in = null;
		try {

			String urlName = url + "?" + param;
			System.out.println("sendGet :" + urlName);
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection(); // 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*"); // 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect(); // 建立实际的连接
			Map<String, List<String>> map = conn.getHeaderFields(); // 获取所有响应头字段
			for (String key : map.keySet()) { // 遍历所有的响应头字段
				System.out.println(key + "--->" + map.get(key));
			}
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream())); // 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}

		} catch (Exception e) {

			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();

		} finally { // 使用finally块来关闭输入流

			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return result;
	}

	/**
	 *
	 * @Title: sendGet
	 * @author dengwenbing
	 * @Description: 向指定URL发送POST方法的请求
	 *
	 * @param url	发送请求的URL
	 * @param param	请求参数，请求参数应该是name1=value1&name2=value2的形式
	 * @return
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			System.out.println("sendPost :" + realUrl);
			URLConnection conn = realUrl.openConnection(); // 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*"); // 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setDoOutput(true); // 发送POST请求必须设置如下两行
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream()); // 获取URLConnection对象对应的输出流s
			out.print(param); // 发送请求参数
			out.flush(); // flush输出流的缓冲
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream())); // 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally { // 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}