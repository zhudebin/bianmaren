package com.bianmaren.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 网站设置表
 * Created by bianmaren on 2016-06-17.
 * QQ:441889070
 */
@Entity
@Table(name = "t_sys_setting")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_sys_setting_sequence")
public class SysSetting extends BaseEntity{

    //设置类型
    public enum SettingType{
        setting,
        log,
        templ
    }

    private SettingType settingType = SettingType.setting;

    private String name; //设置名称
    private String value; //设置值

    private String remark; //备注

    private Boolean is_system = false; //是否是系统内置

    @NotNull
    public SettingType getSettingType() {
        return settingType;
    }
    public void setSettingType(SettingType settingType) {
        this.settingType = settingType;
    }

    @NotNull
    public Boolean getIs_system() {
        return is_system;
    }
    public void setIs_system(Boolean is_system) {
        this.is_system = is_system;
    }

    @NotNull
    @NotEmpty
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysSetting{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
