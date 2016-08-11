/**
 * @Description: 管理员角色表
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 管理员角色表
 */
@Entity
@Table(name = "t_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_role_sequence")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -6614052029623997372L;
	
	/** 名称 */
	private String name;
	
	/** 是否内置 */
	private Boolean isSystem;
	
	/** 描述 */
	private String description;
	
	/** 权限 */
	private List<String> authorities = new ArrayList<String>();
	
	/** 管理员 */
	private Set<Admin> admins = new HashSet<Admin>();

	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, updatable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@Length(max = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ElementCollection
	@CollectionTable(name = "t_role_authority")
	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

}