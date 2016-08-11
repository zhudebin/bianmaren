/**
 * @Description: 会员信息表实体类
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import com.bianmaren.interceptor.MemberInterceptor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 */
@Entity
@Table(name = "t_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_member_sequence")
public class Member extends BaseEntity {

	private static final long serialVersionUID = 1533130686714725835L;

	public enum Gender {male, female}

	//会员类型
	public enum MemberType{
		员工,
		顾客
	}

	public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";
	public static final String SESION_NAME = "loginMemberSession";

	private MemberType memberType;

	private String openid;	//微信openid
	private String unionid; 	//微信unionid
	private String mobile;	//移动电话 【用户登录】
	private String username;	//用户名
	private String realname;	//名称
	private String password;	//密码
	private String email = "";	//邮箱
	private Long experience = 0L;	//经验值
	private Long points = 0L;	//积分
	private BigDecimal balance = BigDecimal.ZERO;//余额
	private Boolean isEnabled = true;	//是否可用
	private Boolean isLocked = false;//是否锁定
	private Integer loginFailureCount = 0;//登录失败错误
	private Date lockedDate;//锁定日期
	private String registerIp;//注册IP
	private String loginIp;//登录IP
	private Date loginDate;//登录日期
	private Gender gender;//性别
	private Date birth;//生日
	private String avator;//头像

	private String addressProvince;	//地址-省
	private String addressCity;	//地址-市
	private String addressCounty;	//地址-县/区
	private String addressDetail;	//地址-详细地址
	private String user_community;	//小区名称

	private String device_tokens; //设备唯一标识
	private String device_type; //设备类型 ios ,android

	private  String address_latitude; //上次登录纬度
	private  String address_longitude; //地址经度


	@NotNull
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public String getAddress_latitude() {
		return address_latitude;
	}

	public void setAddress_latitude(String address_latitude) {
		this.address_latitude = address_latitude;
	}

	public String getAddress_longitude() {
		return address_longitude;
	}

	public void setAddress_longitude(String address_longitude) {
		this.address_longitude = address_longitude;
	}

	@NotNull
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean enabled) {
		isEnabled = enabled;
	}

	@NotNull
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean locked) {
		isLocked = locked;
	}

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

	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}

	public String getAddressProvince() {
		return addressProvince;
	}
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressCounty() {
		return addressCounty;
	}
	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}

	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getUser_community() {
		return user_community;
	}
	public void setUser_community(String user_community) {
		this.user_community = user_community;
	}

	public String getDevice_tokens() {
		return device_tokens;
	}
	public void setDevice_tokens(String device_tokens) {
		this.device_tokens = device_tokens;
	}

	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@NotNull
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

	@NotNull
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Min(0)
	public Long getExperience() {
		return experience;
	}
	public void setExperience(Long experience) {
		this.experience = experience;
	}

	@Min(0)
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}

	@Min(0)
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Member{" +
				"openid='" + openid + '\'' +
				", unionid='" + unionid + '\'' +
				", mobile='" + mobile + '\'' +
				", username='" + username + '\'' +
				", realname='" + realname + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", experience=" + experience +
				", points=" + points +
				", balance=" + balance +
				", isEnabled=" + isEnabled +
				", isLocked=" + isLocked +
				", loginFailureCount=" + loginFailureCount +
				", lockedDate=" + lockedDate +
				", registerIp='" + registerIp + '\'' +
				", loginIp='" + loginIp + '\'' +
				", loginDate=" + loginDate +
				", gender=" + gender +
				", birth=" + birth +
				", avator='" + avator + '\'' +
				", addressProvince='" + addressProvince + '\'' +
				", addressCity='" + addressCity + '\'' +
				", addressCounty='" + addressCounty + '\'' +
				", addressDetail='" + addressDetail + '\'' +
				", user_community='" + user_community + '\'' +
				", device_tokens='" + device_tokens + '\'' +
				", device_type='" + device_type + '\'' +
				'}';
	}
}

