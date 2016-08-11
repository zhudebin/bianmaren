package com.bianmaren.service;

import com.bianmaren.entity.SysSetting;

import java.util.List;

/**
 * Created by bianmaren on 2016-06-17.
 * QQ:441889070
 */
public interface SysSettingService extends BaseService<SysSetting, Long> {

    boolean isExistSetting(SysSetting sysSetting);
    SysSetting getSettingByName(String name);


    SysSetting find(Long id);
    void delete(Long id);
    void delete(Long... ids);
    void delete(SysSetting entity);
    SysSetting update(SysSetting entity);
    SysSetting update(SysSetting entity, String... ignoreProperties);
    List<SysSetting> getAllTemplate();
    List<SysSetting> getAllSetting();
    List<SysSetting> getAllLogConfig();

    String readTemplate(String name);
    String readTemplate(SysSetting sysSetting);
    void writeTemplate(Long id, String content);
    void writeTemplate(SysSetting template, String content);


}
