package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Admin;
import com.bianmaren.entity.BaseEntity.Save;
import com.bianmaren.entity.Role;
import com.bianmaren.service.AdminService;
import com.bianmaren.service.RoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

/**
 * Controller - 管理员
 */
@Controller("adminAdminController")
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (adminService.usernameExists(username)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add")
	public String add(ModelMap model) {
		model.addAttribute("roles", roleService.findAll());
		return "/admin/admin/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes, Long warehouseId) {
		List<Role> roles = roleService.findList(roleIds);

		admin.setRoles(new HashSet<Role>(roles));

		if(null == admin.getIsEnabled()){
			admin.setIsEnabled(false);
		}

		if (!isValid(admin, Save.class)) {
			return ERROR_VIEW;
		}
		if (adminService.usernameExists(admin.getUsername())) {
			request.setAttribute("content","邮箱已经存在");
			return ERROR_VIEW;
		}
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		admin.setIsLocked(false);
		admin.setLoginFailureCount(0);
		admin.setLockedDate(null);
		admin.setLoginDate(null);
		admin.setLoginIp(null);
		adminService.save(admin);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("admin", adminService.find(id));
		return "/admin/admin/edit";
	}

	/**
	 * 更新
	 */
	/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes,Long warehouseId) {
		List<Role> roles = roleService.findList(roleIds);
		if(null == admin.getIsEnabled()){
			admin.setIsEnabled(false);
		}
		admin.setRoles(new HashSet<Role>(roles));
		if (!isValid(admin)) {
			return ERROR_VIEW;
		}
		Admin pAdmin = adminService.find(admin.getId());
		if (pAdmin == null) {
			return ERROR_VIEW;
		}
		if (pAdmin.getIsLocked() && !admin.getIsLocked()) {
			admin.setLoginFailureCount(0);
			admin.setLockedDate(null);
		} else {
			admin.setIsLocked(pAdmin.getIsLocked());
			admin.setLoginFailureCount(pAdmin.getLoginFailureCount());
			admin.setLockedDate(pAdmin.getLockedDate());
		}
		adminService.update(admin, "username", "loginDate", "loginIp", "orders");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}*/

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Admin admin, Long[] roleIds, RedirectAttributes redirectAttributes,Long warehouseId) {
		List<Role> roles = roleService.findList(roleIds);
		Admin pAdmin = adminService.find(admin.getId());
		if (pAdmin == null) {
			return ERROR_VIEW;
		}
		if(null == admin.getIsEnabled()){
			pAdmin.setIsEnabled(false);
		}
		pAdmin.setIsEnabled(admin.getIsEnabled());
		pAdmin.setRoles(new HashSet<Role>(roles));
		pAdmin.setName(admin.getName());
		pAdmin.setMobile(admin.getMobile());
		pAdmin.setEmail(admin.getEmail());
		pAdmin.setDepartment(admin.getDepartment());
		pAdmin.setWeiXinScanCode(admin.getWeiXinScanCode());
		pAdmin.setZhiFuBaoScanCode(admin.getZhiFuBaoScanCode());
		pAdmin.setUsername(admin.getUsername());
		pAdmin.setHeadPortrait(admin.getHeadPortrait());
		pAdmin.setIntroduction(admin.getIntroduction());
		if (pAdmin.getIsLocked() && !admin.getIsLocked()) {
			pAdmin.setLoginFailureCount(0);
			pAdmin.setLockedDate(null);
		}
		adminService.update(pAdmin);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", adminService.findPage(pageable));
		return "/admin/admin/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= adminService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		adminService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("admin", adminService.find(id));
		return "/admin/admin/view";
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/modify_password", method = RequestMethod.GET)
	public String modify_password(Long id, ModelMap model) {
		model.addAttribute("admin", adminService.find(id));
		return "/admin/admin/modify_password";
	}

	/**
	 * 更新密码
	 */
	@RequestMapping(value = "/update_password", method = RequestMethod.POST)
	public String update_password(Long id, ModelMap model,String beforepassword, String newPassword , RedirectAttributes redirectAttributes) {
	  Admin admin = adminService.find(id);
      if(null==admin){
		  addFlashMessage(redirectAttributes, Message.error("用户不存在"));
		  return "redirect:list.html";
	  }
	  if (StringUtils.isNotEmpty(admin.getPassword())) {
		  String retpassword = DigestUtils.md5Hex(beforepassword);
		  if(!retpassword.equals(admin.getPassword())){
			  addFlashMessage(redirectAttributes, Message.error("原密码错误"));
			  return "redirect:list.html";
		  }
	  }
      if(null!=newPassword){
		  String updatePassword = DigestUtils.md5Hex(newPassword);
		  admin.setPassword(updatePassword);
	  }
		adminService.update(admin);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

}