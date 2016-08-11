package com.bianmaren.spider;

import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.entity.dto.SpiderArticle;
import com.bianmaren.service.ArticleCategoryService;
import com.bianmaren.util.DateUtils;
import com.bianmaren.util.SpringUtils;
import com.bianmaren.util.Tools;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by bianmaren on 2016-07-28.
 * QQ:441889070
 */
public class TuicoolSpider  extends BaseSpider {

    private String articleCategoryName = "编程";
    private String articleCategoryName2 = "设计";
    private String articleCategoryName3 = "互联网";


    public void createSpiderArticle(String url,String articleCategoryName,List<SpiderArticle> result){
        ArticleCategoryService articleCategoryService = SpringUtils.getBean("articleCategoryServiceImpl", ArticleCategoryService.class);
        ArticleCategory articleCategory = null;
        if(null != articleCategoryService){
            articleCategory = articleCategoryService.getArticleCategoryByName(articleCategoryName);
        }
        Map<String,String> articleMap = getArticleContent(url);
        SpiderArticle spiderArticle = new SpiderArticle();
        spiderArticle.setArticleCategory(articleCategory);
        spiderArticle.setReprintedUrl(url);
        if(Tools.vaildeParam(articleMap.get("title"))){
            spiderArticle.setTitle(articleMap.get("title"));
            spiderArticle.setTags(articleMap.get("tags"));
            spiderArticle.setContent(articleMap.get("content"));
            result.add(spiderArticle);
        }
    }

    @Override
    public List<SpiderArticle> getArticles() {

        List<SpiderArticle> result = new ArrayList<>();

        List<String> articleUrlList = new ArrayList<>();
        List<String> articleUrlList1 = getAvailableUrl("http://www.tuicool.com/ah/20/0?lang=1");
        List<String> articleUrlList2 = getAvailableUrl("http://www.tuicool.com/ah/20/1?lang=1");
        List<String> articleUrlList3 = getAvailableUrl("http://www.tuicool.com/ah/20/2?lang=1");
        articleUrlList.addAll(articleUrlList1);
        articleUrlList.addAll(articleUrlList2);
        articleUrlList.addAll(articleUrlList3);

        System.out.println("##TuicoolSpider## getArticles articleUrlList"+articleUrlList);

        for(String url:articleUrlList){
            createSpiderArticle(url,articleCategoryName,result);
        }


        List<String> articleUrlList_b = new ArrayList<>();
        List<String> articleUrlList1_b = getAvailableUrl("http://www.tuicool.com/ah/108000000/0?lang=1");
        List<String> articleUrlList2_b = getAvailableUrl("http://www.tuicool.com/ah/108000000/1?lang=1");
        List<String> articleUrlList3_b = getAvailableUrl("http://www.tuicool.com/ah/108000000/2?lang=1");
        articleUrlList_b.addAll(articleUrlList1_b);
        articleUrlList_b.addAll(articleUrlList2_b);
        articleUrlList_b.addAll(articleUrlList3_b);

        System.out.println("##TuicoolSpider## getArticles articleUrlList_b"+articleUrlList_b);

        for(String url:articleUrlList_b){
            createSpiderArticle(url,articleCategoryName2,result);
        }

        List<String> articleUrlList_c = new ArrayList<>();
        List<String> articleUrlList1_c = getAvailableUrl("http://www.tuicool.com/ah/101000000/0?lang=1");
        List<String> articleUrlList2_c = getAvailableUrl("http://www.tuicool.com/ah/101000000/1?lang=1");
        List<String> articleUrlList3_c = getAvailableUrl("http://www.tuicool.com/ah/101000000/2?lang=1");
        List<String> articleUrlList4_c = getAvailableUrl("http://www.tuicool.com/ah/101040000/0?lang=1");
        List<String> articleUrlList5_c = getAvailableUrl("http://www.tuicool.com/ah/101040000/1?lang=1");
        List<String> articleUrlList6_c = getAvailableUrl("http://www.tuicool.com/ah/101040000/2?lang=1");
        List<String> articleUrlList7_c = getAvailableUrl("http://www.tuicool.com/ah/101050000/0?lang=1");
        List<String> articleUrlList8_c = getAvailableUrl("http://www.tuicool.com/ah/101050000/1?lang=1");
        List<String> articleUrlList9_c = getAvailableUrl("http://www.tuicool.com/ah/101050000/2?lang=1");

        articleUrlList_c.addAll(articleUrlList1_c);
        articleUrlList_c.addAll(articleUrlList2_c);
        articleUrlList_c.addAll(articleUrlList3_c);
        articleUrlList_c.addAll(articleUrlList4_c);
        articleUrlList_c.addAll(articleUrlList5_c);
        articleUrlList_c.addAll(articleUrlList6_c);
        articleUrlList_c.addAll(articleUrlList7_c);
        articleUrlList_c.addAll(articleUrlList8_c);
        articleUrlList_c.addAll(articleUrlList9_c);

        System.out.println("##TuicoolSpider## getArticles articleUrlList_c"+articleUrlList_c);

        for(String url:articleUrlList_c){
            createSpiderArticle(url,articleCategoryName3,result);
        }

        return result;
    }

    /**
     * 确认文章是否是最新的文章
     * @param flag 07-28
     * @return
     */
    public boolean checkArticleNew(String flag){
        String now = DateUtils.getStringFromDate(DateUtils.getYesterday(new Date()),"MM-dd");
        if(!now.equals(flag)){
            return false;
        }
        return true;
    }

    public List<String> getAvailableUrl(String url){
        List<String> urlList = new ArrayList<>();

        try {

            Document doc = getPageDocument(url);

            if(null != doc){

                Elements a_titles = doc.select(".list_article .single_fake");
                for(int i=0;i<a_titles.size();i++){
                    Element article = a_titles.get(i);

                    //获取文章链接
                    Element article_a = article.select(".article_title a").get(0);
                    String article_url = article_a.attr("href");

                    //获取时间标记
                    Element meta_tip = article.select(".meta-tip span").get(1);
                    String date_falg = meta_tip.html().replace("<i class=\"icon-time icon\"></i> ","").split(" ")[0];

                    if(checkArticleNew(date_falg)){
                        urlList.add("http://www.tuicool.com"+article_url);
                    }

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return urlList;
    }


    public Map<String,String> getArticleContent(String url){

        Map<String,String> mapResult = new HashMap<>();
        Document doc = getPageDocument(url);

        try{
            //获取标题
            Element titleElement = doc.select(".contant h1").get(0);
            String title = titleElement.html();

            //获取标签
            Elements tags = doc.select(".contant .article_meta div").get(2).select("a span");
            String tag = "";
            for(int i=0;i<tags.size();i++){
                tag+=tags.get(i).html()+",";
            }

            //获取内容
            Element body_e = doc.select(".article_body").get(0);
            String content = getContent(body_e);

            mapResult.put("title",title);
            mapResult.put("content",content);
            mapResult.put("tags",tag);
        }catch (Exception e){
            e.printStackTrace();

            mapResult.put("title","");
            mapResult.put("content","");
            mapResult.put("tags","");

        }

        System.out.println("##TuicoolSpider## getArticleContent mapResult"+mapResult);
        return mapResult;
    }

    public static void main(String[] args){

        TuicoolSpider t = new TuicoolSpider();
        t.getArticles();
    }
}
