package com.bianmaren.dao;

import java.util.Date;
import java.util.List;

import com.bianmaren.Filter;
import com.bianmaren.Order;
import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.entity.Article;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.entity.Tag;

public interface ArticleDao extends BaseDao<Article, Long> {

	List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders);

	List<Article> findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count);

	Page<Article> findPage(ArticleCategory articleCategory, List<Tag> tags, Pageable pageable);

}