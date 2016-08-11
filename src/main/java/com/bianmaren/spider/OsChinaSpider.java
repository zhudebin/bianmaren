package com.bianmaren.spider;

import com.bianmaren.Setting;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.entity.dto.CodeFileJson;
import com.bianmaren.entity.dto.CodeFragment;
import com.bianmaren.entity.dto.CodeJson;
import com.bianmaren.entity.dto.SpiderArticle;
import com.bianmaren.service.ArticleCategoryService;
import com.bianmaren.service.FileService;
import com.bianmaren.service.TagService;
import com.bianmaren.util.FreemarkerUtils;
import com.bianmaren.util.SettingUtils;
import com.bianmaren.util.SpringUtils;
import com.bianmaren.util.Tools;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * 抓取开源中国代码页面的最新文章
 * Created by bianmaren on 2016-07-25.
 * QQ:441889070
 */
public class OsChinaSpider extends BaseSpider implements ServletContextAware {

    private String articleCategoryName = "编程";  //抓取到的文章所属分类

    /** servletContext */
    private ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     *
     * @Title: getGetOsChinaCodePost
     * @author dengwenbing
     * @Description: 抓取文章内容
     * @date: 2015年4月11日
     *
     * @param url
     * @return
     */
    public CodeJson getGetOsChinaCodePost(String url){

        FileService fileService = SpringUtils.getBean("fileServiceImpl", FileService.class);


        long starttime = System.currentTimeMillis();
        System.out.println("##获取代码文章## url:"+url+" [开始]");
        CodeJson codeJson = new CodeJson();
        ArrayList<CodeFragment> code_fragment = new ArrayList<>();//代码片段数组
        ArrayList<String> code_pic_list = new ArrayList<>();//代码图片
        ArrayList<CodeFileJson> code_files_list = new ArrayList<>();//代码文件

        if(null == url && "".equals(url)){
            return null;
        }
        try {
            Document doc = getPageDocument(url);
            if(null != doc){
                /**
                 * 获取文章语言和分类
                 */
                Element osc_banner_dd = doc.select("#OSC_Banner dl dd").get(0);
                Elements osc_banner_a = osc_banner_dd.select("a");
                String lang = osc_banner_a.get(1).text();
                String category = osc_banner_a.get(2).text();
                codeJson.setLang(lang);
                codeJson.setCategory(category);

                /**
                 * 获取文章标题
                 */
                Element title_element = doc.select("#OSC_Screen #OSC_Content .Title .QTitle h1 a").get(0);
                String title = title_element.text();
                codeJson.setCode_title(title);

                /**
                 * 获取文章描述
                 */
                Element describe_element = doc.select("#OSC_Screen #OSC_Content .Content .detail").get(0);
                String describe = describe_element.text();
                codeJson.setTa_code_outline(describe);

                /**
                 * 获取附件
                 */
                Elements attache_element = doc.select("#OSC_Screen #OSC_Content .CodeEntity .code_pieces .code_piece");

                for(Element attache:attache_element){
                    Elements code_imgs = attache.select(".code_img");
                    Elements code_pres = attache.select("pre");
//					Elements code_files = attache.getElementsByAttributeValueContaining("href","/action/code/download");
                    if(!code_imgs.isEmpty()){
                        System.out.println(code_imgs);
                        for(Element code_img_one:code_imgs){
                            String code_img_url = code_img_one.getElementsByTag("img").attr("src");

                            String upload_result = fileService.upLoadCodeImageByNetUrl(code_img_url);
                            if(null != upload_result && upload_result.length()>0){
                                code_pic_list.add(upload_result);
                                System.out.println(upload_result);
                            }
                        }
                    }
                    if(!code_pres.isEmpty()){
                        for(Element code_pre_one:code_pres){
                            String code_conent = Tools.toHtml(code_pre_one.html());
                            String code_attr_class= code_pre_one.attr("class");
                            String code_lang = "others";
                            try {
                                code_lang = code_attr_class.split(";")[0].split(":")[1];
                            } catch (Exception e) {
                                e.printStackTrace();
                                code_lang = "others";
                            }
                            CodeFragment codeFragment= new CodeFragment();
                            codeFragment.setCode_lang(code_lang);
                            codeFragment.setCode_content(code_conent);
                            code_fragment.add(codeFragment);
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        codeJson.setCode_pic(code_pic_list);
        codeJson.setCode_fragment(code_fragment);
        codeJson.setCode_files(code_files_list);
        System.out.println(codeJson);

        long endtime = System.currentTimeMillis();
        System.out.println("##获取代码文章## url:"+url+" [结束] "+(endtime-starttime)+"ms");
        return codeJson;
    }

    public List<String> getOsChinaCodePostUrl(){
        List<String> urlList = new ArrayList<>();
        try {
            System.out.println("##抓去开源中国最新的代码文章##");
            Document doc = getPageDocument("http://www.oschina.net/code/list");

            if(null != doc){
                Elements codeslines = doc.select(".code_list ul li");
                int length = codeslines.size();
                for(int i=0;i<length;i++){
                    Element elementTmp = codeslines.get(i).getElementsByClass("code_title").get(0);
                    Element element_a1 = elementTmp.getElementsByTag("a").get(0);
                    Element element_span = elementTmp.getElementsByTag("span").get(0);

                    String url = element_a1.attr("href");
                    String date = element_span.text().split(" ")[0];

                    /**
                     * 只抓去时间为**小时前,或者是**分钟前,或者**秒前的文章地址
                     */
                    if(-1 != date.indexOf("小时前") || -1!=date.indexOf("分钟前") || -1!=date.indexOf("秒前")){
                        urlList.add(url);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }

    @Override
    public List<SpiderArticle> getArticles() {

        List<SpiderArticle> result = new ArrayList<>();

        ArticleCategoryService articleCategoryService = SpringUtils.getBean("articleCategoryServiceImpl", ArticleCategoryService.class);

        ArticleCategory articleCategory = articleCategoryService.getArticleCategoryByName(articleCategoryName);
        List<String> urlList = getOsChinaCodePostUrl();

        for(String url:urlList){
            CodeJson codeJson =  getGetOsChinaCodePost(url);

            SpiderArticle spiderArticle = new SpiderArticle();
            spiderArticle.setReprintedUrl(url);
            spiderArticle.setTitle(codeJson.getCode_title());
            spiderArticle.setTags(codeJson.getLang()+","+codeJson.getCategory());
            StringBuffer content = new StringBuffer();
            //添加描述
            if(Tools.vaildeParam(codeJson.getTa_code_outline())){
                content.append("<p>"+codeJson.getTa_code_outline()+"</p>");
            }

            //添加图片
            ArrayList<String> code_pics = codeJson.getCode_pic();
            for(String pic:code_pics){
                content.append("<p><img src='"+pic+"' class='img-responsive'/></p>" );
            }

            //添加代码
            ArrayList<CodeFragment> codeFragmentList = codeJson.getCode_fragment();
            for(CodeFragment code:codeFragmentList){
                content.append("<pre class='brush:"+code.getCode_lang().toLowerCase()+";toolbar:false "+code.getCode_lang().toLowerCase()+"'>"+Tools.html(code.getCode_content())+"</pre>" );
            }

            spiderArticle.setContent(content.toString());
            spiderArticle.setArticleCategory(articleCategory);

            result.add(spiderArticle);
        }

        return result;
    }
}
