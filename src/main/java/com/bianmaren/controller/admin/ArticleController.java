package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.Setting;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Article;
import com.bianmaren.entity.Tag;
import com.bianmaren.entity.Tag.Type;
import com.bianmaren.service.AdminService;
import com.bianmaren.service.ArticleCategoryService;
import com.bianmaren.service.ArticleService;
import com.bianmaren.service.TagService;
import com.bianmaren.util.SettingUtils;
import com.bianmaren.util.Tools;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.HashSet;

/**
 * Controller - 文章
 */
@Controller("adminArticleController")
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name = "tagServiceImpl")
	private TagService tagService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;


	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
		model.addAttribute("tags", tagService.findList(Type.article));
		return "/admin/article/add";
	}





	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Article article, Long articleCategoryId, Long[] tagIds, RedirectAttributes redirectAttributes) {
		article.setArticleCategory(articleCategoryService.find(articleCategoryId));
		article.setTags(new HashSet<Tag>(tagService.findList(tagIds)));

		if(null == article.getIsPublication()){
			article.setIsPublication(false);
		}
		if(null == article.getIsTop()){
			article.setIsTop(false);
		}

		article.setHeadImg(Tools.getArticleContentFirstImg(article.getContent()));


		article.setHits(0L);
		article.setPageNumber(null);
		article.setAdmin(adminService.getCurrent());//添加管理员

		//设置SEO属性
		article.setSeoTitle(article.getTitle() + " " + SettingUtils.get().getSiteName());
		article.setSeoKeywords(Tools.getSeoKeywordsByTitle(article.getTitle()));
		article.setSeoDescription(article.getTitle()+ " " + SettingUtils.get().getSiteName() + " " + SettingUtils.get().getSystemDescription());

		if (!isValid(article)) {
			return ERROR_VIEW;
		}

		articleService.save(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("articleCategoryTree", articleCategoryService.findTree());
		model.addAttribute("tags", tagService.findList(Type.article));
		model.addAttribute("article", articleService.find(id));
		return "/admin/article/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Article article, Long articleCategoryId, Long[] tagIds, RedirectAttributes redirectAttributes) {

		Article articleOld = articleService.find(article.getId()); //查询到实体，管理员不会被更新

		article.setArticleCategory(articleCategoryService.find(articleCategoryId));
		article.setTags(new HashSet<Tag>(tagService.findList(tagIds)));

		if(null == article.getIsPublication()){
			article.setIsPublication(false);
		}
		if(null == article.getIsTop()){
			article.setIsTop(false);
		}

		article.setHeadImg(Tools.getArticleContentFirstImg(article.getContent()));

		//设置SEO属性
		article.setSeoTitle(article.getTitle() + " " + SettingUtils.get().getSiteName());
		article.setSeoKeywords(Tools.getSeoKeywordsByTitle(article.getTitle()));
		article.setSeoDescription(article.getTitle()+ " " + SettingUtils.get().getSiteName() + " " + SettingUtils.get().getSystemDescription());

		BeanUtils.copyProperties(articleOld,article,new String[]{
			"headImg","title","content","seoTitle","seoKeywords","seoDescription","isPublication","isTop"
		});
		if (!isValid(article)) {
			return ERROR_VIEW;
		}

		articleService.update(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", articleService.findPage(pageable));
		return "/admin/article/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		articleService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}