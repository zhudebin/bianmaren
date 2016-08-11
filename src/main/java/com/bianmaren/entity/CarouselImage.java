package com.bianmaren.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 轮播图
 * Created by bianmaren on 2016-06-18.
 * QQ:441889070
 */
@Entity
@Table(name = "t_carousel_images")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_carousel_images_sequence")
public class CarouselImage extends OrderEntity{

    private String title; //标题
    private String url; //跳转链接
    private String pic; //图片
    private Boolean is_enable ; //是否启用
    public Boolean getIs_enable() {
        return is_enable;
    }
    public void setIs_enable(Boolean is_enable) {
        this.is_enable = is_enable;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @NotNull
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "CarouselImages{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
