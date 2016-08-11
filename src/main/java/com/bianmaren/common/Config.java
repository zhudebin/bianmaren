package com.bianmaren.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Config
{
  public static String getConfig(String arg)
  {
    String rtn = "";
    try
    {
      Properties prop = new Properties();
      InputStream inputStream = new Config().getClass().getClassLoader().getResourceAsStream("config.properties");
      prop.load(inputStream);
      rtn = prop.getProperty(arg);
      inputStream.close();
    }
    catch (IOException e)
    {
      System.out.println("读取配置文件错误：" + e.getMessage());
    }
    return rtn;
  }
  
  public static String getConfig2UTF8(String arg)
  {
    String rtn = getConfig(arg);
    try
    {
      rtn = new String(rtn.getBytes("ISO-8859-1"), "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      System.out.println("读取配置文件错误：" + e.getMessage());
    }
    return rtn;
  }
  
  public static void main(String[] args) {}
}
