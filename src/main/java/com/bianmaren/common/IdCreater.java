package com.bianmaren.common;

import java.util.Random;

public class IdCreater
{
  public static String IdAsString()
  {
    String dateTime = DateTime.getDateTime("yyyyMMddHHmmss");
    Random random = new Random();
    int r = random.nextInt(99999);
    return String.valueOf(dateTime + r);
  }
  
  public static Long IdAsLong()
  {
    String dateTime = DateTime.getDateTime("yyyyMMddHHmmss");
    Random random = new Random();
    int r = random.nextInt(99999);
    return Long.valueOf(Long.parseLong(dateTime + r));
  }
  
  public static void main(String[] args)
  {
    System.out.println(IdAsString());
  }
}
