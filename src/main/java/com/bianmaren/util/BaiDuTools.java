package com.bianmaren.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


/**
 * Created by bianmaren on 2016-07-30.
 * QQ:441889070
 */
public class BaiDuTools {

    final static String url = "http://data.zz.baidu.com/urls?site=www.bianmaren.com&token=VcEgKYyWjf6D2lZA";


    /**
     * 百度SEO，向百度主动推送链接
     * @param urls
     */
    public static void postUrlAction(List<String> urls){

        if(null ==urls || urls.size() == 0 ){
            System.out.println("### BaiDuTools ### postUrlAction() urls is empty");
            return;
        }

        System.out.println("### BaiDuTools ### postUrlAction() start");
        System.out.println("### BaiDuTools ### postUrlAction() start urls:"+urls);
        try{
            String[] arr = urls.toArray(new String[urls.size()]);
            System.out.println("### BaiDuTools ### postUrlAction() start arr:"+arr);
            String json = postUrl(url, arr);
            System.out.println("### BaiDuTools ### postUrlAction() start json:"+json);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  百度链接实时推送
     * @param PostUrl
     * @param Parameters
     * @return
     */
    public static String postUrl(String PostUrl,String[] Parameters){
        if(null == PostUrl || null == Parameters || Parameters.length ==0){
            return null;
        }
        String result="";
        PrintWriter out=null;
        BufferedReader in=null;
        try {
            //建立URL之间的连接
            URLConnection conn=new URL(PostUrl).openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("Host","data.zz.baidu.com");
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");

            //发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //获取conn对应的输出流
            out=new PrintWriter(conn.getOutputStream());
            //发送请求参数
            String param = "";
            for(String s : Parameters){
                param += s+"\n";
            }
            out.print(param.trim());
            //进行输出流的缓冲
            out.flush();
            //通过BufferedReader输入流来读取Url的响应
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!= null){
                result += line;
            }

        } catch (Exception e) {
            System.out.println("发送post请求出现异常！"+e);
            e.printStackTrace();
        } finally{
            try{
                if(out != null){
                    out.close();
                }
                if(in!= null){
                    in.close();
                }

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }



}
