package com.bianmaren.freemarker.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.service.ArticleCategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 模板指令 - 顶级文章分类列表
 * 
 */
@Component("articleCategoryRootListDirective")
public class ArticleCategoryRootListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "articleCategories";

	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<ArticleCategory> articleCategories;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		if (useCache) {
			articleCategories = articleCategoryService.findRoots(count, cacheRegion);
		} else {
			articleCategories = articleCategoryService.findRoots(count);
		}
		setLocalVariable(VARIABLE_NAME, articleCategories, env, body);
	}

}