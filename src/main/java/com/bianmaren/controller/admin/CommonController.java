package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Setting;
import com.bianmaren.service.MessageService;
import com.bianmaren.service.RSAService;
import com.bianmaren.util.SettingUtils;
import com.bianmaren.util.SpringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware {

	@Resource(name = "messageServiceImpl")
	private MessageService messageService;
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	
	/**
	 * 
	 * @Description: 后台主页框架首页
	 * @author 441889070@qq.com
	 * @date 2015年10月23日 上午10:38:43
	 * @param
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "/admin/common/main";
	}
	
	/**
	 * 后台主页内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		Setting setting = SettingUtils.get();
		model.addAttribute("systemName", setting.getSiteName());
		model.addAttribute("systemDescription", setting.getSystemDescription());
		model.addAttribute("officialSite", setting.getSiteUrl());
		model.addAttribute("javaVersion", System.getProperty("java.version"));
		model.addAttribute("javaHome", System.getProperty("java.home"));
		model.addAttribute("osName", System.getProperty("os.name"));
		model.addAttribute("osArch", System.getProperty("os.arch"));
		model.addAttribute("serverInfo", servletContext.getServerInfo());
		model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		model.addAttribute("unreadMessageCount", messageService.count(null, false));
		
		Runtime r = Runtime.getRuntime();
		model.addAttribute("JVMTotalMemory", r.totalMemory());
		model.addAttribute("JVMFreeMemory", r.freeMemory());
		
		return "/admin/common/index";
	}


	/**
	 * 
	 * @Description: 系统错误页面
	 * @author 441889070@qq.com
	 * @date 2015年10月23日 上午10:38:19
	 * @param
	 */
	@RequestMapping("/error")
	public String error() {
		return "/admin/common/error";
	}

	/**
	 * 
	 * @Description: 认证异常页面
	 * @author 441889070@qq.com
	 * @date 2015年10月23日 上午10:38:07
	 * @param
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "unauthorized");
			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		return "/admin/common/unauthorized";
	}

	
	/**
	 * 
	 * @Description: 登录页面
	 * @author 441889070@qq.com
	 * @date 2015年10月22日 上午11:48:25
	 * @param
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Model model) {
		String captchaId = UUID.randomUUID().toString();
		Setting setting = SettingUtils.get();
		model.addAttribute("captchaId", captchaId);
		model.addAttribute("setting", setting);
		
		
		//已经登录直接跳转到后台主页
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser.getPrincipal()){
			return "redirect:/admin/common/main.html";
		}
		
		//RSA加密相关
		RSAPublicKey publicKey = rsaService.generateKey(request);
		String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
		String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
		model.addAttribute("ras_modulus", modulus);
		model.addAttribute("ras_exponent", exponent);
		
		
		//获取登录异常消息
		String loginMessage = null;
		String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if(null != loginFailure){
			if(loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")){
				loginMessage = "admin.captcha.invalid";
			}else if(loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")){
				loginMessage = "admin.login.unknownAccount";
			}else if(loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")){
				loginMessage = "admin.login.disabledAccount";
			}else if(loginFailure.equals("org.apache.shiro.authc.LockedAccountException")){
				loginMessage = "admin.login.lockedAccount";
			}else if(loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")){
					loginMessage = "admin.login.incorrectCredentials";
			}else if(loginFailure.equals("org.apache.shiro.authc.AuthenticationException")){
				loginMessage = "admin.login.authentication";
			}
		}
		
		
		if(!model.containsAttribute("loginMessage")){//防止覆盖登出的消息
			Message loginMessageOnject = new Message(Message.Type.error,null != loginMessage?SpringUtils.getMessage(loginMessage):"");
			model.addAttribute("loginMessage", loginMessageOnject);
		}
		
		return "/admin/login";
	}
	
	/**
	 * 
	 * @Description: 登出动作
	 * @author 441889070@qq.com
	 * @date 2015年10月23日 下午1:15:29
	 * @param
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes redirectAttributes) {
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
		try {
			SecurityUtils.getSubject().logout();
	        redirectAttributes.addFlashAttribute("loginMessage", new Message(Message.Type.success,SpringUtils.getMessage("admin.logout.success")));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("loginMessage", new Message(Message.Type.error,SpringUtils.getMessage("admin.logout.error")+","+e.getMessage()));
		}
        return "redirect:/admin/common/login.html";
	}
	
	
	
}