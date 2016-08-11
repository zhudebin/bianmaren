package com.bianmaren.job;

import com.bianmaren.Filter;
import com.bianmaren.Setting;
import com.bianmaren.entity.Admin;
import com.bianmaren.entity.Article;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.entity.Tag;
import com.bianmaren.entity.dto.CodeFileJson;
import com.bianmaren.entity.dto.CodeFragment;
import com.bianmaren.entity.dto.CodeJson;
import com.bianmaren.entity.dto.SpiderArticle;
import com.bianmaren.notifications.BaseNotifications;
import com.bianmaren.service.*;
import com.bianmaren.spider.BaseSpider;
import com.bianmaren.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by bianmaren on 2016-07-24.
 * QQ:441889070
 */
@Component("spiderJob")
@Lazy(false)
public class SpiderJob{

    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;

    @Scheduled(cron = "${job.spider.do}")
    public void doOsChinaSpider() {
        System.out.println("##SpiderJob##  -->doSpider()");
        ThreadPoolTaskExecutor taskExecutor = SpringUtils.getBean("taskExecutor",ThreadPoolTaskExecutor.class);
        try {
            for (Class<?> cc : ClassUtils.getAllAssignedClass(BaseSpider.class)) {

                Object invokeTester = cc.getConstructor(new Class[]{}).newInstance(new Object[]{});
                Method addMethod = cc.getMethod("getArticles", new Class[]{});
                final Object result = addMethod.invoke(invokeTester, new Object[]{});

                //以线程的方式去保存文章
                taskExecutor.execute(new Thread(){
                    @Override
                    public void run() {
                        List<SpiderArticle> articleList = (List<SpiderArticle>)result;
                        List<String> urls = publicArticle(articleList);

                        //主动向百度推送文章
                        BaiDuTools.postUrlAction(urls);
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据以逗号分隔的标签么，获取标签集合
     * @param names
     * @return
     */
    public Set<Tag> getTagListByNames(String names){
        Set<Tag> tagList = new HashSet<>();
        if(Tools.vaildeParam(names)){
            String[] tag_arr = names.split(",");
            for(String tmp:tag_arr){
                if(Tools.vaildeParam(tmp)){
                    Tag tag = tagService.getTagByName(tmp.trim());
                    tagList.add(tag);
                }
            }
        }

        return tagList;
    }

    /**
     * 发布文章
     * @param articleList
     */
    public List<String> publicArticle(List<SpiderArticle> articleList){

        List<String> urls = new ArrayList<>();

        if(null == articleList || articleList.size() == 0){
            return urls;
        }

        for(SpiderArticle spiderArticle:articleList){

            //根据文章标题，判断文章是否存在
            Filter[] filters = new Filter[1];
            filters[0] = Filter.eq("title",spiderArticle.getTitle());
            Long count = articleService.count(filters);
            if(null != count && count > 0){
                continue;
            }

            Set<Tag> tagList = getTagListByNames(spiderArticle.getTags());

            Article article = new Article();
            article.setReprintedUrl(spiderArticle.getReprintedUrl());
            article.setTitle(spiderArticle.getTitle());
            article.setContent(spiderArticle.getContent());
            article.setArticleCategory(spiderArticle.getArticleCategory());
            article.setTags(tagList);

            Random random = new Random();
            int id = random.nextInt(3)%(3-1+1) + 1;
            Admin admin = adminService.find(Long.valueOf(id));
            article.setAdmin(admin);

            //设置SEO属性
            article.setSeoTitle(article.getTitle() + " " + SettingUtils.get().getSiteName());
            article.setSeoKeywords(Tools.getSeoKeywordsByTitle(article.getTitle()));
            article.setSeoDescription(article.getTitle()+ " " + SettingUtils.get().getSiteName() + " " + SettingUtils.get().getSystemDescription());

            /**
             * 产生随机时间
             */
            String now_string = DateUtils.getStringFromDate(DateUtils.getYesterday(new Date()), "yyyy-MM-dd");
            String random_time_start = now_string+" 00:00:00";
            String random_time_end = now_string+" 23:00:00";
            Date random_date = DateUtils.randomDate(random_time_start, random_time_end);
            article.setCreateDate(new Timestamp(random_date.getTime()));
            article.setModifyDate(new Timestamp(random_date.getTime()));

            article.setHits(Long.valueOf(new Random().nextInt(100)));
            article.setIsPublication(true);
            article.setIsTop(false);
            article.setHeadImg(Tools.getArticleContentFirstImg(article.getContent()));

            articleService.save(article);

            urls.add(SettingUtils.get().getSiteUrl()+ article.getPath());
        }

        return  urls;

    }


}
