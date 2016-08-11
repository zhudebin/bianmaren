package com.bianmaren.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Des
{
  private static String m_key = "@#E$^T&!";
  
  public static String encrypt(String arg)
  {
    String rtn = "";
    try
    {
      rtn = encrypt(null, arg);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public static String encrypt(String key, String arg)
    throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
  {
    String rtn = null;
    if ((key != null) && ("".equals(key))) {
      m_key = key;
    }
    SecureRandom sr = new SecureRandom();
    DESKeySpec dks = new DESKeySpec(m_key.getBytes());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);
    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(1, securekey, sr);
    byte[] enBytes = cipher.doFinal(arg.getBytes());
    rtn = byte2hex(enBytes);
    
    return rtn;
  }
  
  public static String decrypt(String arg)
  {
    String rtn = "";
    try
    {
      rtn = decrypt(null, arg);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public static String decrypt(String key, String arg)
    throws Exception
  {
    String rtn = null;
    if ((key != null) && ("".equals(key))) {
      m_key = key;
    }
    SecureRandom sr = new SecureRandom();
    
    DESKeySpec dks = new DESKeySpec(m_key.getBytes());
    

    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey securekey = keyFactory.generateSecret(dks);
    
    Cipher cipher = Cipher.getInstance("DES");
    
    cipher.init(2, securekey, sr);
    

    byte[] dcBype = cipher.doFinal(hex2byte(arg.getBytes()));
    rtn = new String(dcBype);
    
    return rtn;
  }
  
  public static byte[] hex2byte(byte[] b)
  {
    if (b.length % 2 != 0) {
      throw new IllegalArgumentException("byte 流错误");
    }
    byte[] b2 = new byte[b.length / 2];
    for (int n = 0; n < b.length; n += 2)
    {
      String item = new String(b, n, 2);
      b2[(n / 2)] = ((byte)Integer.parseInt(item, 16));
    }
    return b2;
  }
  
  public static String byte2hex(byte[] b)
  {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++)
    {
      stmp = Integer.toHexString(b[n] & 0xFF);
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      } else {
        hs = hs + stmp;
      }
    }
    return hs.toUpperCase();
  }
  
  public static void main(String[] args)
    throws Exception
  {
    System.out.println(encrypt("a"));
  }
}
