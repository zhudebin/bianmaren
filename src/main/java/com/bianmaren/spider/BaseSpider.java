package com.bianmaren.spider;

import com.bianmaren.Filter;
import com.bianmaren.Setting;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.entity.dto.SpiderArticle;
import com.bianmaren.service.ArticleCategoryService;
import com.bianmaren.service.FileService;
import com.bianmaren.util.FreemarkerUtils;
import com.bianmaren.util.SettingUtils;
import com.bianmaren.util.SpringUtils;
import com.bianmaren.util.Tools;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by bianmaren on 2016-07-25.
 * QQ:441889070
 */
public abstract class BaseSpider {

    /**
     * 抓取最新的文章
     * @return
     */
    public abstract List<SpiderArticle> getArticles();


    /**
     * 抓取页面
     * @param url
     * @return
     */
    public Document getPageDocument(String url){
        Document doc = null;
        try{
            Connection con= Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                    .timeout(30000);
            Connection.Response resp = con.execute();

            if (resp.statusCode() == 200){
                doc = con.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }


    /**
     * 根据文章元素，获取文章，并且图片上传到服务器
     * @param body_e
     * @return
     */
    public String getContent(Element body_e){
        FileService fileService = SpringUtils.getBean("fileServiceImpl", FileService.class);

        String content = body_e.html();

        //获取所有图片
        Elements imgs_e = body_e.select("img");
        for(int i=0;i<imgs_e.size();i++){
            Element img = imgs_e.get(i);
            String url = img.attr("src");
            String upload_result = fileService.upLoadCodeImageByNetUrl(url);

            content = content.replace(url,upload_result);
        }

        return content;
    }

}
