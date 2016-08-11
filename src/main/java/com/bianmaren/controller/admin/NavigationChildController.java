package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Navigation;
import com.bianmaren.entity.NavigationChild;
import com.bianmaren.service.NavigationChildService;
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
@Controller("adminNavigationChildController")
@RequestMapping("/admin/navigation_child")
public class NavigationChildController  extends BaseController {

    @Resource(name = "navigationServiceImpl")
    NavigationService navigationService;
    @Resource(name = "navigationChildServiceImpl")
    NavigationChildService navigationChildService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model,Long pid) {

        Navigation navigation = navigationService.find(pid);
        model.addAttribute("navigation",navigation);

        return "/admin/navigation_child/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(NavigationChild navigationChild, RedirectAttributes redirectAttributes,Long pid) {

        Navigation navigation = navigationService.find(pid);
        navigationChild.setParentNavigation(navigation);

        if (!isValid(navigationChild)) {
            return ERROR_VIEW;
        }
        navigationChildService.save(navigationChild);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html?pid="+pid;
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("navigation", navigationChildService.find(id));
        return "/admin/navigation_child/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(NavigationChild navigationChild, RedirectAttributes redirectAttributes,Long pid) {

        Navigation navigation = navigationService.find(pid);
        navigationChild.setParentNavigation(navigation);

        if (!isValid(navigationChild)) {
            return ERROR_VIEW;
        }
        navigationChildService.update(navigationChild);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html?pid="+pid;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list(Pageable pageable, ModelMap model,Long pid) {
        Navigation navigation = navigationService.find(pid);
        model.addAttribute("page", navigationChildService.findPage(pageable));
        model.addAttribute("navigation", navigation);
        return "/admin/navigation_child/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        navigationChildService.delete(ids);
        return SUCCESS_MESSAGE;
    }


}
