package com.bianmaren.entity.dto;

import com.bianmaren.entity.ArticleCategory;

/**
 * 文章抓取时需要抓取的字段
 * Created by bianmaren on 2016-07-25.
 * QQ:441889070
 */
public class SpiderArticle {

    public ArticleCategory articleCategory; //文章分类

    private String reprintedUrl; //转载地址
    private String title; //标题
    private String content;//内容
    private String tags;  //多个以逗号分隔


    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getReprintedUrl() {
        return reprintedUrl;
    }
    public void setReprintedUrl(String reprintedUrl) {
        this.reprintedUrl = reprintedUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
}
