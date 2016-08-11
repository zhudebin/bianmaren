/**
 * @Description: 管理员信息表实体类
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_admin")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_admin_sequence")
public class Admin extends BaseEntity {

	private static final long serialVersionUID = -7519486823153844426L;
	
	/** 登录名*/
	private String username;
	
	/** 手机 */
	private String mobile;
	
	/** 密码*/
	private String password;
	
	/** email*/
	private String email="";
	
	/** 姓名 */
	private String name;
	
	/** 部门 */
	private String department;
	
	/** 是否启用 */	
	private Boolean isEnabled;
	
	/** 是否锁定 */
	private Boolean isLocked=false;
	
	/** 连续登录失败次数 */
	private Integer loginFailureCount=0;
	
	/** 锁定日期 */
	private Date lockedDate;
	
	/** 最后登录日期 */
	private Date loginDate;
	
	/** 最后登录IP */
	private String loginIp;

	/** 头像 */
	private String headPortrait;

	/** 介绍 */
	private String introduction;

	/** 微信扫码 */
	private String weiXinScanCode;

	/** 支付宝扫码 */
	private String zhiFuBaoScanCode;

	/** 角色 */
	private Set<Role> roles = new HashSet<Role>();
	

	public Admin(){}
	public Admin(Long id){
	     super.setId(id);
	}

	@JsonProperty
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
	@Length(min = 2, max = 20)
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[^\\s&\"<>]+$")
	@Length(min = 4, max = 20)
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Email
	@Length(max = 200)
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(max = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(max = 200)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@NotNull
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(nullable = false)
	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_admin_role")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getWeiXinScanCode() {
		return weiXinScanCode;
	}

	public void setWeiXinScanCode(String weiXinScanCode) {
		this.weiXinScanCode = weiXinScanCode;
	}

	public String getZhiFuBaoScanCode() {
		return zhiFuBaoScanCode;
	}

	public void setZhiFuBaoScanCode(String zhiFuBaoScanCode) {
		this.zhiFuBaoScanCode = zhiFuBaoScanCode;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}