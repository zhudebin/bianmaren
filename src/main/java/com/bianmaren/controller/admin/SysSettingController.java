package com.bianmaren.controller.admin;

import com.bianmaren.Filter;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.SysSetting;
import com.bianmaren.service.CacheService;
import com.bianmaren.service.SysSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bianmaren on 2016-06-17.
 * QQ:441889070
 */
@Controller("adminSysSettingController")
@RequestMapping("/admin/sys_setting")
public class SysSettingController extends BaseController{

    @Resource(name = "sysSettingServiceImpl")
    SysSettingService sysSettingService;
    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;


    /**
     * 设置列表
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Pageable pageable, ModelMap model,SysSetting.SettingType settingType) {

        //默认是系统设置
        if(null == settingType){
            settingType = SysSetting.SettingType.setting;
        }
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("settingType",settingType));

        model.addAttribute("page", sysSettingService.findPage(pageable,filters,null));
        model.addAttribute("settingType", settingType);
        model.addAttribute("settingTypeList", SysSetting.SettingType.values());

        return "/admin/sys_setting/list";
    }

    /**
     * 添加设置
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("settingTypeList",SysSetting.SettingType.values());
        return "/admin/sys_setting/add";
    }

    /**
     * 编辑设置
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model,Long id) {
        model.addAttribute("settingTypeList",SysSetting.SettingType.values());
        model.addAttribute("sysSetting",sysSettingService.find(id));
        return "/admin/sys_setting/edit";
    }


    @RequestMapping(value = "/delete")
    public String delete(Model model,Long id,RedirectAttributes redirectAttributes) {
        SysSetting sysSetting = sysSettingService.find(id);
        if(null != sysSetting){
            //只有非系统设置才可以被删除
            if(!sysSetting.getIs_system()){
                sysSettingService.delete(sysSetting);
            }
        }
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 保存
     * @param sysSetting
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(SysSetting sysSetting, RedirectAttributes redirectAttributes) {

        if(null != sysSetting){
            if(sysSettingService.isExistSetting(sysSetting)){
                SysSetting sysSettingOld = sysSettingService.getSettingByName(sysSetting.getName());
                BeanUtils.copyProperties(sysSetting,sysSettingOld,new String[]{"id","is_system","modifyDate","createDate"});
                if(!isValid(sysSettingOld)){
                    return ERROR_VIEW;
                }
                sysSettingService.update(sysSettingOld);
            }else{
                if(!isValid(sysSetting)){
                    return ERROR_VIEW;
                }
                sysSettingService.save(sysSetting);
            }
        }

        //向freemarker中注入最新的setting值
        cacheService.clearSetting();

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

}
