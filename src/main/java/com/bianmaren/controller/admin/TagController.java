package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.BaseEntity.Save;
import com.bianmaren.entity.Tag;
import com.bianmaren.entity.Tag.Type;
import com.bianmaren.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Controller - 标签
 */
@Controller("adminTagController")
@RequestMapping("/admin/tag")
public class TagController extends BaseController {

	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("types", Type.values());
		return "/admin/tag/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Tag tag, RedirectAttributes redirectAttributes) {
		if (!isValid(tag, Save.class)) {
			return ERROR_VIEW;
		}
		tag.setArticles(null);
		tagService.save(tag);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("types", Type.values());
		model.addAttribute("tag", tagService.find(id));
		return "/admin/tag/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Tag tag, RedirectAttributes redirectAttributes) {
		if (!isValid(tag)) {
			return ERROR_VIEW;
		}
		tagService.update(tag, "type", "articles", "products");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", tagService.findPage(pageable));
		return "/admin/tag/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		tagService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}