package com.bianmaren.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 导航
 * Created by bianmaren on 2016-06-30.
 * QQ:441889070
 */
@Entity
@Table(name = "t_navigation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_navigation_sequence")
public class Navigation extends OrderEntity {

    private String name; //导航名称
    private String url; //导航链接
    private Boolean is_enable; //是否启用

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<NavigationChild> navigationChildList;

    @OneToMany(mappedBy = "parentNavigation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<NavigationChild> getNavigationChildList(){
        return navigationChildList;
    }
    public void setNavigationChildList(List<NavigationChild> navigationChildList){
        this.navigationChildList = navigationChildList;
    }

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

    @Override
    public String toString() {
        return "Navigation{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", is_enable=" + is_enable +
                '}';
    }
}
