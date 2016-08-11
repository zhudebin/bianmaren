package com.bianmaren.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Client
{
  public String URL;
  private CloseableHttpClient client;
  
  public Client(String URL)
  {
    this.URL = URL;
    this.client = HttpClients.createDefault();
    if (URL.toLowerCase().startsWith("https")) {
      try
      {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
        {
          public boolean isTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException
          {
            return true;
          }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        
        this.client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public String doPost(Map<String, String> param)
  {
    if ((param == null) || (param.size() == 0)) {
      return null;
    }
    String rtn = null;
    try
    {
      Iterator<String> it = param.keySet().iterator();
      List<NameValuePair> list = new ArrayList();
      while (it.hasNext())
      {
        String key = (String)it.next();
        list.add(new BasicNameValuePair(key, (String)param.get(key)));
      }
      HttpPost post = new HttpPost(this.URL);
      

      post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
      CloseableHttpResponse response = this.client.execute(post);
      int statusCode = response.getStatusLine().getStatusCode();
      System.out.println(statusCode);
      if (statusCode == 200)
      {
        rtn = EntityUtils.toString(response.getEntity());
        rtn = rtn.replaceAll("\r", "");
      }
      response.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public String doPost(String content)
  {
    String rtn = null;
    try
    {
      HttpPost post = new HttpPost(this.URL);
      StringEntity se = new StringEntity(content, "UTF-8");
      post.setEntity(se);
      CloseableHttpResponse response = this.client.execute(post);
      int statusCode = response.getStatusLine().getStatusCode();
      System.out.println(statusCode);
      if (statusCode == 200)
      {
        rtn = EntityUtils.toString(response.getEntity());
        rtn = rtn.replaceAll("\r", "");
      }
      response.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public String doGet()
  {
    String rtn = null;
    try
    {
      HttpGet get = new HttpGet(this.URL);
      CloseableHttpResponse response = this.client.execute(get);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == 200)
      {
        rtn = EntityUtils.toString(response.getEntity());
        rtn = rtn.replaceAll("\r", "");
      }
      response.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public InputStream downFile()
  {
    InputStream rtn = null;
    try
    {
      HttpGet get = new HttpGet(this.URL);
      CloseableHttpResponse response = this.client.execute(get);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == 200)
      {
        HttpEntity entity = response.getEntity();
        rtn = entity.getContent();
      }
      response.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rtn;
  }
  
  public void close()
  {
    try
    {
      if (this.client != null) {
        this.client.close();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
