package com.bianmaren.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getStringFromDate(Date date,String formatstring){
		try{
			DateFormat format = new SimpleDateFormat(formatstring);
			return date == null ? null:format.format(date);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String fromDate(Date date,String pattern){
		if(pattern!=null){			
			sdf.applyPattern(pattern);
		}
		if(date==null) return null;
		
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @Description: 获取两个时间之间相差的分钟数
	 * @author 441889070@qq.com
	 * @date 2015年11月29日 上午9:32:19
	 * @param
	 */
	public static Long dateDiffMinute(Date startDate,Date endDate){
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long diff = endDate.getTime() - startDate.getTime();
		Long diff_min = (Long)(diff/nm);
		long min = diff_min;//计算差多少分钟
		return min;
	}
	
	
	/*
	 * author		:		dengwenbing
	 * describe		:		计算日期的一号
	 */
	public static Date getMonthFirstDay(Date time){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(time);
		c.add(Calendar.DAY_OF_MONTH,1-c.get(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	/*
	 * author		:		dengwenbing
	 * describe		:		计算星期的礼拜一
	 */
	public static Date getWeekFirstDay(Date time){
		Calendar date=Calendar.getInstance(Locale.CHINA);
		date.setTime(time);
	    date.setFirstDayOfWeek(Calendar.MONDAY);
	    date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    return date.getTime();
	}
	
	/**
	 * 
	 * @Description: 获取一天的开始时间
	 * @author 441889070@qq.com
	 * @date 2015年12月4日 下午12:23:08
	 * @param
	 */
	public static Date getDayStart(Date time){
		long current=time.getTime();//当前时间毫秒数
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		return new Date(zero);
	}
	
	/**
	 * 
	 * @Description: 获取一天的结束时间
	 * @author 441889070@qq.com
	 * @date 2015年12月4日 下午12:23:27
	 * @param
	 */
	public static Date getDayEnd(Date time){
		long current=time.getTime();//当前时间毫秒数
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
		return new Date(twelve);
	}
	
	/**
	 * 获取昨天日期
	 * @Description: TODO
	 * @author 441889070@qq.com
	 * @date 2015年12月4日 下午12:28:28
	 * @param
	 */
	public static Date getYesterday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}
	
	public static Date getTomorrow(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}
	
	
	public static Date getDateAfter(Date date, int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	public static Date strToDate(String date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateResut = null;
		try {
			dateResut = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateResut;
	}

	/**
	 *
	 * @Title: randomDate
	 * @author liukai
	 * @Description: 生成随机时间
	 * @date: 2015年5月22日
	 *
	 * @param beginDate 格式yyyy-MM-dd HH:mm:ss
	 * @param endDate 格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @Title: random
	 * @author dengwenbing
	 * @Description: 生成随机时间
	 * @date: 2015年5月22日
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}


}
