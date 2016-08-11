package com.bianmaren.service.impl;

import com.bianmaren.Filter;
import com.bianmaren.dao.SysSettingDao;
import com.bianmaren.entity.SysSetting;
import com.bianmaren.service.SysSettingService;
import com.bianmaren.util.Tools;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bianmaren on 2016-06-17.
 * QQ:441889070
 */
@Service("sysSettingServiceImpl")
public class SysSettingServiceImpl extends BaseServiceImpl<SysSetting, Long> implements SysSettingService,ServletContextAware {

    private ServletContext servletContext;

    @Resource(name = "sysSettingDaoImpl")
    private SysSettingDao sysSettingDao;

    @Resource(name = "sysSettingDaoImpl")
    public void setBaseDao(SysSettingDao sysSettingDao) {
        super.setBaseDao(sysSettingDao);
    }

    @Value("${template.loader_path}")
    private String[] templateLoaderPaths;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public void save(SysSetting entity) {
        super.save(entity);
    }

    @Transactional(readOnly = true)
    @Cacheable("setting")
    public SysSetting find(Long id) {
       return super.find(id);
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public void delete(Long... ids) {
        super.delete(ids);
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public void delete(SysSetting entity) {
        super.delete(entity);
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public SysSetting update(SysSetting entity) {
        return super.update(entity);
    }

    @Transactional
    @CacheEvict(value={"setting","template","logConfig"},allEntries=true)
    public SysSetting update(SysSetting entity, String... ignoreProperties) {
        return super.update(entity,ignoreProperties);
    }

    /**
     * 根据设置值，看是否存在这个设置
     * @param sysSetting
     * @return
     */
    public boolean isExistSetting(SysSetting sysSetting){

        if(null == sysSetting){
            return false;
        }

        String name = sysSetting.getName();
        if(!Tools.vaildeParam(name)){
            return false;
        }

        Filter[] filters = new Filter[1];
        filters[0] = Filter.eq("name",name.trim());

        Long count = count(filters);
        if(null != count && count >0 ){
            return true;
        }

        return false;
    }

    /**
     * 根据设置名称获取设置值
     * @param name
     * @return
     */
    @Cacheable("setting")
    public SysSetting getSettingByName(String name){
        if(!Tools.vaildeParam(name)){
            return null;
        }
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("name",name.trim()));
        List<SysSetting> sysSettingList = findList(1,filters,null);
        if(null != sysSettingList && sysSettingList.size()>0){
            return sysSettingList.get(0);
        }
        return null;
    }

    /**
     * 获取模板设置
     * @return
     */
    @Cacheable("template")
    public List<SysSetting> getAllTemplate() {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("settingType",SysSetting.SettingType.templ));
        List<SysSetting> sysSettingList = findList(null,filters,null);
        return sysSettingList;
    }

    @Cacheable("setting")
    public List<SysSetting> getAllSetting() {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("settingType",SysSetting.SettingType.setting));
        List<SysSetting> sysSettingList = findList(null,filters,null);
        return sysSettingList;
    }

    @Cacheable("logConfig")
    public List<SysSetting> getAllLogConfig() {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("settingType",SysSetting.SettingType.log));
        List<SysSetting> sysSettingList = findList(null,filters,null);
        return sysSettingList;
    }


    /**
     * 用户页面名字读取模板内容
     * @param name
     * @return
     */
    public String readTemplate(String name) {
        SysSetting sysSetting = getSettingByName(name);
        return readTemplate(sysSetting);
    }

    /**
     * 根据设置实体读取模板
     * @param sysSetting
     * @return
     */
    public String readTemplate(SysSetting sysSetting) {
        String templatePath = servletContext.getRealPath(templateLoaderPaths[0] + sysSetting.getValue());
        File templateFile = new File(templatePath);
        String templateContent = null;
        try {
            templateContent = FileUtils.readFileToString(templateFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return templateContent;
    }


    /**
     * 根据实体类ID去写模板
     * @param id
     * @param content
     */
    public void writeTemplate(Long id, String content) {
        SysSetting sysSetting = find(id);
        writeTemplate(sysSetting, content);
    }

    /**
     * 根据实体去写模板
     * @param template
     * @param content
     */
    public void writeTemplate(SysSetting template, String content) {
        String templatePath = servletContext.getRealPath(templateLoaderPaths[0] + template.getValue());
        File templateFile = new File(templatePath);
        try {
            FileUtils.writeStringToFile(templateFile, content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
