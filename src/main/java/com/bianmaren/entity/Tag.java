/**
 * @Description: 标签表
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "t_tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_tag_sequence")
public class Tag extends OrderEntity {

	private static final long serialVersionUID = -2735037966597250149L;

	/**
	 * 类型
	 */
	public enum Type {
		
		article,/** 文章标签 */

		product/** 商品标签 */
	
	};
	
	/** 名称 */
	private String name;

	/** 类型 */
	private Type type;

	/** 图标 */
	private String icon;

	/** 备注 */
	private String memo;

	/** 文章 */
	private Set<Article> articles = new HashSet<Article>();

	@JsonProperty
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	@NotNull(groups = Save.class)
	@Column(nullable = false, updatable = false)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@JsonProperty
	@Length(max = 200)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@JsonProperty
	@Length(max = 200)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}


}