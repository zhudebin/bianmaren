package com.bianmaren.job;

import com.alibaba.fastjson.JSON;
import com.bianmaren.entity.FamousAphorism;
import com.bianmaren.service.FamousAphorismService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 每天生成一句名言
 * Created by bianmaren on 2016-07-23.
 * QQ:441889070
 */
@Component("famousAphorismJob")
@Lazy(false)
public class FamousAphorismJob {

    @Resource(name = "famousAphorismServiceImpl")
    private FamousAphorismService famousAphorismService;


    /**
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "716a5037461e817039e07ae02e5bb44f");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Scheduled(cron = "${job.famousAphorismJob.create}")
    public void create() {

        System.out.println("###### FamousAphorismJob ######  create()");

        /**
         * 调用http://apistore.baidu.com/ 里面的接口，随机获取名言
         */
        String response = request("http://apis.baidu.com/txapi/dictum/dictum","");

        System.out.println("###### FamousAphorismJob ######  response:"+response);

        Map<String,Object> map_response = JSON.parseObject(response);

        if(null == map_response){
            return;
        }

        Integer code = (Integer)map_response.get("code");

        if(null != code && code == 200){

            List<Map<String,String>> newslist =  (List<Map<String,String>>)map_response.get("newslist");

            Map<String,String> data = newslist.get(0);
            if(null != data){
                FamousAphorism famousAphorism = new FamousAphorism();
                famousAphorism.setMrname(data.get("mrname"));
                famousAphorism.setContent(data.get("content"));

                famousAphorismService.save(famousAphorism);
            }

        }

    }


}
