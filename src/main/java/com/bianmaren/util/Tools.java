package com.bianmaren.util;

import com.bianmaren.controller.api.ApiResult;
import com.bianmaren.entity.MemberRank;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

/**
 * 工具类
 * @author dengwenbing
 *
 */
public class Tools {


	/**
	 *
	 * @Description: 填充ApiResult
	 * @author 441889070@qq.com
	 * @date 2015年11月12日 下午2:19:58
	 * @param
	 */
	public static <T> void fillApiResult(ApiResult<T> result, Boolean success, String resultCode, String resultMsg, T data){
		result.setSuccess(success);
		result.setResultCode(resultCode);
		result.setResultMsg(resultMsg);
		result.setData(data);
	}

	/**
	 * 
	 * @Description: 参数验证
	 * @author 441889070@qq.com
	 * @date 2015年12月6日 下午10:18:48
	 * @param
	 */
	public static boolean vaildeParam(Object... args){
    	for (int i = 0; i < args.length; i++) {
 		   if(null == args[i]){
 			   return false;
 		   }else{
 			  if(args[i].getClass().equals(String.class)){
				   if("".equals(args[i])){
					  return false;
				   }
			   }
 		   }
 		}
    	return true;
     }
	 
	 /**
	  * 
	  * @Description: 获取Pingxx支付方式名称
	  * @author 441889070@qq.com
	  * @date 2015年12月6日 下午10:19:20
	  * @param
	  */
	 public static String getPayMathodNameByChannel(String channel){
		 if("alipay".equals(channel)){
			 return "支付宝手机支付";
		 }else if("alipay_wap".equals(channel)){
			 return "支付宝手机网页支付";
		 }else if("alipay_qr".equals(channel)){
			 return "支付宝扫码支付";
		 }else if("alipay_pc_direct".equals(channel)){
			 return "支付宝 PC 网页支付";
		 }else if("apple_pay".equals(channel)){
			 return "Apple Pay";
		 }else if("bfb".equals(channel)){
			 return "百度钱包移动快捷支付";
		 }else if("bfb_wap".equals(channel)){
			 return "百度钱包手机网页支付";
		 }else if("upacp".equals(channel)){
			 return "银联全渠道支付";
		 }else if("upacp_wap".equals(channel)){
			 return "银联全渠道手机网页支付";
		 }else if("upacp_pc".equals(channel)){
			 return "银联 PC 网页支付";
		 }else if("upmp".equals(channel)){
			 return "银联手机支付";
		 }else if("upmp_wap".equals(channel)){
			 return "银联手机网页支付";
		 }else if("wx".equals(channel)){
			 return "微信支付";
		 }else if("wx_pub".equals(channel)){
			 return "微信公众账号支付";
		 }else if("wx_pub_qr".equals(channel)){
			 return "微信公众账号扫码支付";
		 }else if("yeepay_wap".equals(channel)){
			 return "易宝手机网页支付";
		 }else if("jdpay_wap".equals(channel)){
			 return "京东手机网页支付";
		 }else if("cnp_u".equals(channel)){
			 return "应用内快捷支付（银联）";
		 }else if("cnp_f".equals(channel)){
			 return "应用内快捷支外卡）";
		 }else{
			 return "其他";
		 }
		 
	 }
	 
	 /**
	  * 
	  * @Description: 获取用户等级
	  * @author 441889070@qq.com
	  * @date 2015年12月6日 下午10:20:00
	  * @param
	  */
	 public static String getUserLevelName(List<MemberRank> memberRanks,BigDecimal amount){
		 for(MemberRank rank:memberRanks){
			 if(-1 != amount.compareTo(rank.getAmount())){
				 return rank.getName();
			 }
		 }
		 return "无等级";
	 }
	 
	 public static String getExceptionStack(Exception e) {
	          StackTraceElement[] stackTraceElements = e.getStackTrace();
	          String result = e.toString() + "\n";
	          for (int index = stackTraceElements.length - 1; index >= 0; --index) {
	                  result += "at [" + stackTraceElements[index].getClassName() + ",";
	                  result += stackTraceElements[index].getFileName() + ",";
	                  result += stackTraceElements[index].getMethodName() + ",";
	                  result += stackTraceElements[index].getLineNumber() + "]\n";
	          }
	         return result;
	 }

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		dir.delete();
		return true;
	}

	/**
	 * 生成捐赠的订单ID
	 * @param project_id
	 * @return
     */
	public static String getDonateOrderNum(Long project_id){
		String orderNum = "D"+project_id+System.currentTimeMillis();
		orderNum += StringUtils.randomString(5);
		return orderNum;
	}

	/**
	 *
	 * @Title: getFileNameAndExByUrl
	 * @author dengwenbing
	 * @Description: 根据网络资源路径获取文件名和后缀，格式"文件名;后缀"
	 * @date: 2015年4月11日
	 *
	 * @param url
	 * @return
	 */
	public static String getFileNameAndExByUrl(String url){
		try {
			String[] url_split = url.split("/");
			String name = url_split[url_split.length-1];
			String suffixes = name.split("\\.")[name.split("\\.").length - 1];
			return getFileNameNoEx(name)+";"+suffixes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis()+";png";
	}

	/**
	 *
	 * @Title: getFileNameNoEx
	 * @author dengwenbing
	 * @Description: 获取不带后缀的文件名
	 * @date: 2015年4月10日
	 *
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot >-1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public static String getRandomFileName(String prefix){
		String reslut = "";
		Date now = new Date();
		try {
			String nowStr = DateUtils.getStringFromDate(now,"yyyyMMdd");
			String name = nowStr+(now.getTime());
			reslut = prefix+name;
		} catch (Exception e) {
			e.printStackTrace();
			reslut = prefix+now.getTime();
		}
		return reslut;
	}


	public static String toHtml(String content) {
		if(content==null) return "";
		String html = content;

		html = html.replace( "&apos;", "'");
		html = html.replaceAll( "&amp;", "&");
		html = html.replace( "&quot;", "\"");
		html = html.replace( "&nbsp;&nbsp;", "\t");
		html = html.replace( "&nbsp;", " ");
		html = html.replace("&lt;", "<");
		html = html.replaceAll( "&gt;", ">");

		return html;
	}

	public static String html(String content) {
		if(content==null) return "";
		String html = content;
		html = html.replaceAll( "&", "&amp;");
		html = html.replace( "\"", "&quot;");  //"
		html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
		html = html.replace( " ", "&nbsp;");// 替换空格
		html = html.replace("<", "&lt;");
		html = html.replaceAll( ">", "&gt;");
		return html;
	}


	public static String getSeoKeywordsByTitle(String title){

		String seoKeywords = "";
		try {
			Analyzer anal=new IKAnalyzer(true);
			StringReader reader=new StringReader(title);
			//分词
			TokenStream ts=anal.tokenStream("", reader);
			CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
			//遍历分词数据
			while(ts.incrementToken()){
				seoKeywords += term.toString()+",";
			}
			reader.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return seoKeywords+","+SettingUtils.get().getSiteName();
	}

	/**
	 * 获取文章内容的第一张图片
	 * @param content
	 * @return
	 */
	public static String getArticleContentFirstImg(String content){
		if(!Tools.vaildeParam(content)){
			return  null;
		}
		try {
			Document doc = Jsoup.parse(content);
			if(null != doc){
				if(null != doc.select("img")){
					Element img = doc.select("img").get(0);
					if(null != img){
						String url = img.attr("src");
						if(Tools.vaildeParam(url) && -1 != url.indexOf("http")){
							return url;
						}
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}



}
