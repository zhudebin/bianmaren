package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Order;
import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Member;
import com.bianmaren.entity.MemberRank;
import com.bianmaren.service.*;
import com.bianmaren.util.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController{

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "messageServiceImpl")
	private MessageService messageService;

	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_mobile")
	@ResponseBody
	public boolean checkMobile(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}
		if (memberService.mobileExists(mobile)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 检查E-mail是否唯一
	 */
	@RequestMapping(value = "/check_email")
	@ResponseBody
	public boolean checkEmail(String previousEmail, String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		if (memberService.emailUnique(previousEmail, email)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("member", memberService.find(id));
		
		//等级规则
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("amount"));
		List<MemberRank> memberRanks = memberRankService.findList(null, null, orders);
		model.addAttribute("memberRanks", memberRanks);
				
		return "/admin/member/view";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "/admin/member/add";
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Pageable pageable, ModelMap model) {
		Page<Member> memberList = memberService.findPage(pageable);
		model.addAttribute("page", memberList);
		return "/admin/member/list";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,RedirectAttributes redirectAttributes,Member member){
		
		//判断用户存不存在
		if(memberService.mobileExists(member.getMobile())){
			addFlashMessage(redirectAttributes, Message.error("该手机用户已经存在"));
			return "redirect:add.html";
		}

		//注册ip
		String registerIp = "";
		try {
			registerIp = WebUtils.getIp(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		member.setRegisterIp(registerIp);
		
		member.setPassword(DigestUtils.md5Hex(member.getPassword()));
		//实体验证
		if (!isValid(member)) {
			return ERROR_VIEW;
		}

		memberService.save(member);
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
			memberService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {

		model.put("member",memberService.find(id));

		return "/admin/member/edit";
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,RedirectAttributes redirectAttributes,
			Member member,Boolean isEnabled){

		if(null == isEnabled){
			isEnabled = false;
		}
		member.setIsEnabled(isEnabled);

		Member old_member = memberService.find(member.getId());

		BeanUtils.copyProperties(old_member,member,new String[]{"email","avator","username","isEnabled"});

		//实体验证
		if (!isValid(member)) {
			return ERROR_VIEW;
		}
		memberService.update(member);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}


	
	
}
