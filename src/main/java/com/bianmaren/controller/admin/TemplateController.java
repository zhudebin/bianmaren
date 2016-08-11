package com.bianmaren.controller.admin;

import com.bianmaren.Filter;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.SysSetting;
import com.bianmaren.service.SysSettingService;
import com.bianmaren.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller("adminTemplateController")
@RequestMapping("/admin/template")
public class TemplateController extends BaseController {

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Resource(name = "sysSettingServiceImpl")
	private SysSettingService sysSettingService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {

		if (!Tools.vaildeParam(id)) {
			return ERROR_VIEW;
		}

		SysSetting sysSetting = sysSettingService.find(id);
		model.addAttribute("template", sysSetting);
		model.addAttribute("content", sysSettingService.readTemplate(sysSetting));

		return "/admin/template/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Long id, String content, RedirectAttributes redirectAttributes) {
		if (!Tools.vaildeParam(id) || content == null) {
			return ERROR_VIEW;
		}
		sysSettingService.writeTemplate(id, content);
		freeMarkerConfigurer.getConfiguration().clearTemplateCache();
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.html";
	}

	@RequestMapping(value = "/list")
	public String list(Pageable pageable, ModelMap model) {
		List<Filter> filters = new ArrayList<>();
		filters.add(Filter.eq("settingType",SysSetting.SettingType.templ));
		model.addAttribute("page", sysSettingService.findPage(pageable,filters,null));
		return "/admin/template/list";
	}

}