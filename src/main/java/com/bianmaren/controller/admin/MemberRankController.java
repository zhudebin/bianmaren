package com.bianmaren.controller.admin;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.MemberRank;
import com.bianmaren.service.MemberRankService;

@Controller("adminMemberRankController")
@RequestMapping("/admin/member_rank")
public class MemberRankController extends BaseController {

	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;

	/**
	 * 检查名称是否唯一
	 */
	@RequestMapping(value = "/check_name", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkName(String previousName, String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		if (memberRankService.nameUnique(previousName, name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查消费金额是否唯一
	 */
	@RequestMapping(value = "/check_amount", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkAmount(BigDecimal previousAmount, BigDecimal amount) {
		if (amount == null) {
			return false;
		}
		if (memberRankService.amountUnique(previousAmount, amount)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "/admin/member_rank/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(MemberRank memberRank, RedirectAttributes redirectAttributes) {
		if (!isValid(memberRank)) {
			addFlashMessage(redirectAttributes, Message.error("数据格式错误"));
			return "redirect:add.html";
		}
		if (memberRankService.nameExists(memberRank.getName())) {
			addFlashMessage(redirectAttributes, Message.error("用户等级名称已经存在"));
			return "redirect:add.html";
		}
		
		if (memberRank.getAmount() == null || memberRankService.amountExists(memberRank.getAmount())) {
			addFlashMessage(redirectAttributes, Message.error("用户等级消费金额已经存在"));
			return "redirect:add.html";
		}
		
		memberRankService.save(memberRank);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("memberRank", memberRankService.find(id));
		return "/admin/member_rank/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberRank memberRank, RedirectAttributes redirectAttributes) {
		if (!isValid(memberRank)) {
			return ERROR_VIEW;
		}
		MemberRank pMemberRank = memberRankService.find(memberRank.getId());
		if (pMemberRank == null) {
			return ERROR_VIEW;
		}
		if (!memberRankService.nameUnique(pMemberRank.getName(), memberRank.getName())) {
			addFlashMessage(redirectAttributes, Message.error("用户等级名称出现重复"));
			return "redirect:edit.html?id="+memberRank.getId();
		}
		
		if (memberRank.getAmount() == null || !memberRankService.amountUnique(pMemberRank.getAmount(), memberRank.getAmount())) {
			addFlashMessage(redirectAttributes, Message.error("用户等级消费金额出现重复"));
			return "redirect:edit.html?id="+memberRank.getId();
		}
		
		memberRankService.update(memberRank, "members", "promotions");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", memberRankService.findPage(pageable));
		return "/admin/member_rank/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
			memberRankService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

}