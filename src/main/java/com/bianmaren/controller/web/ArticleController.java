package com.bianmaren.controller.web;

import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Article;
import com.bianmaren.entity.ArticleCategory;
import com.bianmaren.exception.ResourceNotFoundException;
import com.bianmaren.service.ArticleCategoryService;
import com.bianmaren.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Controller - 文章
 */
@Controller("webArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 20;

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String list(@PathVariable Long id, Integer pageNumber, ModelMap model) {
		ArticleCategory articleCategory = articleCategoryService.find(id);
		if (articleCategory == null) {
			throw new ResourceNotFoundException();
		}
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		model.addAttribute("articleCategory", articleCategory);
		model.addAttribute("page", articleService.findPage(articleCategory, null, pageable));
		return "/web/article/list";
	}


	/**
	 * 点击数
	 */
	@RequestMapping(value = "/hits/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Long hits(@PathVariable Long id) {
		return articleService.viewHits(id);
	}

	@ResponseBody
	@RequestMapping(value = "/ajaxLoadArticle")
	public Page<Article> ajaxLoadArticle(Pageable pageable){
		return articleService.findPage(pageable);
	}


}