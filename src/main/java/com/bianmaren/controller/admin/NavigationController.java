package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Navigation;
import com.bianmaren.service.NavigationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by bianmaren on 2016-07-01.
 * QQ:441889070
 */
@Controller("adminNavigationController")
@RequestMapping("/admin/navigation")
public class NavigationController extends BaseController{

    @Resource(name = "navigationServiceImpl")
    NavigationService navigationService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/navigation/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Navigation navigation, RedirectAttributes redirectAttributes) {
        navigationService.save(navigation);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("navigation", navigationService.find(id));
        return "/admin/navigation/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Navigation navigation, RedirectAttributes redirectAttributes) {
        if (!isValid(navigation)) {
            return ERROR_VIEW;
        }

        navigationService.update(navigation,new String[]{ "navigationChildList" });

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", navigationService.findPage(pageable));
        return "/admin/navigation/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        navigationService.delete(ids);
        return SUCCESS_MESSAGE;
    }


}
