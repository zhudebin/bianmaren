package com.bianmaren.controller.admin;

import com.alibaba.fastjson.JSON;
import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.FriendLink;
import com.bianmaren.entity.FriendLink.Type;
import com.bianmaren.service.FriendLinkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("adminFriendLinkController")
@RequestMapping("/admin/friend_link")
public class FriendLinkController extends BaseController {

	@Resource(name = "friendLinkServiceImpl")
	private FriendLinkService friendLinkService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("types", Type.values());
		return "/admin/friend_link/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(FriendLink friendLink, RedirectAttributes redirectAttributes) {
		if (!isValid(friendLink)) {
			return ERROR_VIEW;
		}
		if (friendLink.getType() == Type.text) {
			friendLink.setLogo(null);
		} else if (StringUtils.isEmpty(friendLink.getLogo())) {
			return ERROR_VIEW;
		}
		friendLinkService.save(friendLink);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("types", Type.values());
		model.addAttribute("friendLink", friendLinkService.find(id));
		return "/admin/friend_link/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(FriendLink friendLink, RedirectAttributes redirectAttributes) {
		if (!isValid(friendLink)) {
			return ERROR_VIEW;
		}
		if (friendLink.getType() == Type.text) {
			friendLink.setLogo(null);
		} else if (StringUtils.isEmpty(friendLink.getLogo())) {
			return ERROR_VIEW;
		}
		friendLinkService.update(friendLink);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", friendLinkService.findPage(pageable));
		return "/admin/friend_link/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		friendLinkService.delete(ids);
		return SUCCESS_MESSAGE;
	}


	/**
	 * 校验名称重复性
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public String checkName(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		return JSON.toJSONString(false);
	}


}