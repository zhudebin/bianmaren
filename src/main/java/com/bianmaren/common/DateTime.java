package com.bianmaren.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime
{
  public static String getDateTime(String format)
  {
    if ((format == null) || ("".equals(format))) {
      format = "yyyy-MM-dd HH:mm:ss";
    }
    DateFormat df = new SimpleDateFormat(format);
    String value = df.format(new Date());
    return value;
  }
  
  public static String getDateTime()
  {
    return getDateTime("");
  }
  
  public static String isTimeOut(String date, String format)
  {
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Date old;
    try
    {
      old = sdf.parse(date);
    }
    catch (ParseException e)
    {
      
      return "错误时间：" + date;
    }
    int flag = now.compareTo(old);
    if (flag > 0) {
      return "超时";
    }
    if (flag == 0) {
      return "相等";
    }
    return "未超时";
  }
  
  public static long timeDiff(String dateA, String dateB)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date d1 = df.parse(dateA);
      
      Date d2 = df.parse(dateB);
      
      long diff = d1.getTime() - d2.getTime();
      
      return diff / 1000L;
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
    return 0L;
  }
}

