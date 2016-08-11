package com.bianmaren.common;

import java.io.UnsupportedEncodingException;

public class StringEncode
{
  public static String utf8ToGB2312(String arg)
  {
    try
    {
      return new String(arg.getBytes("utf-8"), "gb2312");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String utf8ToIso(String arg)
  {
    try
    {
      return new String(arg.getBytes("utf-8"), "ISO-8859-1");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String gb2312ToUtf8(String arg)
  {
    try
    {
      return new String(arg.getBytes("gb2312"), "utf-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String isoToUtf8(String arg)
  {
    try
    {
      return new String(arg.getBytes("ISO-8859-1"), "utf-8");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
