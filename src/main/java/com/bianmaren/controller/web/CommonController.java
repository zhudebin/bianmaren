package com.bianmaren.controller.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bianmaren.controller.BaseController;
import org.springframework.web.context.ServletContextAware;

@Controller("webCommonController")
@RequestMapping("/common")
public class CommonController extends BaseController implements ServletContextAware {

	/** servletContext */
	private ServletContext servletContext;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * @Description: 资源没找到页面
	 * @author 441889070@qq.com
	 * @date 2015年10月16日 上午11:05:31
	 * @param
	 */
	@RequestMapping(value = "/resource_not_found.html", method = RequestMethod.GET)
	public String resource_not_found() {
		return "/admin/resource_not_found";
	}
	
	/**
	 * 
	 * @Description: 通过该控制器来控制跳转页面
	 * @author 441889070@qq.com
	 * @date 2015年10月22日 上午11:48:33
	 * @param
	 */
	@RequestMapping("/web/{name}")
	public String commonFtl(HttpServletRequest request,HttpServletResponse response,Model model,
			@PathVariable("name") String name) {
		return "/web/"+name;
	}

}
