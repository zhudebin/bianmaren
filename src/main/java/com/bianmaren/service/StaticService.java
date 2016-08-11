
package com.bianmaren.service;

import java.util.Map;

import com.bianmaren.entity.Article;

public interface StaticService {

	int build(String templatePath, String staticPath, Map<String, Object> model);

	int build(String templatePath, String staticPath);

	int build(Article article);


	int buildIndex();

	int buildSitemap();

	int buildOther();

	int buildAll();

	int delete(String staticPath);

	int delete(Article article);

	int deleteIndex();

	int deleteOther();

}