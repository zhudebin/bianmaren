package com.bianmaren.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 子导航
 * Created by bianmaren on 2016-06-30.
 * QQ:441889070
 */

@Entity
@Table(name = "t_navigation_child")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_navigation_child_sequence")
public class NavigationChild extends OrderEntity {

    private String name; //导航名称
    private String url; //导航链接
    private Boolean is_enable; //是否启用

    private Navigation parentNavigation; //父亲导航

    @NotNull
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIs_enable() {
        return is_enable;
    }
    public void setIs_enable(Boolean is_enable) {
        this.is_enable = is_enable;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Navigation getParentNavigation() {
        return parentNavigation;
    }
    public void setParentNavigation(Navigation parentNavigation) {
        this.parentNavigation = parentNavigation;
    }



}
