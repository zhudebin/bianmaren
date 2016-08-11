package com.bianmaren.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 名言警句
 * Created by bianmaren on 2016-07-23.
 * QQ:441889070
 */
@Entity
@Table(name = "t_famous_aphorism")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_famous_aphorism_sequence")
public class FamousAphorism extends BaseEntity{

    private String content; //内容
    private String mrname; //作者

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMrname() {
        return mrname;
    }

    public void setMrname(String mrname) {
        this.mrname = mrname;
    }

    @Override
    public String toString() {
        return "FamousAphorism{" +
                "content='" + content + '\'' +
                ", mrname='" + mrname + '\'' +
                '}';
    }
}
