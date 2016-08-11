package com.bianmaren.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bianmaren.Principal;
import com.bianmaren.Setting;
import com.bianmaren.dao.MemberDao;
import com.bianmaren.entity.Admin;
import com.bianmaren.entity.Member;
import com.bianmaren.service.MemberService;
import com.bianmaren.util.SettingUtils;

@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;

	@Resource(name = "memberDaoImpl")
	public void setBaseDao(MemberDao memberDao) {
		super.setBaseDao(memberDao);
	}

	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return memberDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public boolean mobileExists(String mobile){
		return this.memberDao.mobileExists(mobile);
	}
	
	
	@Transactional(readOnly = true)
	public boolean usernameDisabled(String username) {
		Assert.hasText(username);
		Setting setting = SettingUtils.get();
		return false;
	}

	@Transactional(readOnly = true)
	public boolean emailExists(String email) {
		return memberDao.emailExists(email);
	}

	@Transactional(readOnly = true)
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		} else {
			if (memberDao.emailExists(currentEmail)) {
				return false;
			} else {
				return true;
			}
		}
	}


	@Transactional(readOnly = true)
	public Member findByUsername(String username) {
		return memberDao.findByUsername(username);
	}

	public Member findByMobile(String mobile){
		return this.memberDao.findByMobile(mobile);
	}

	public Member findByEmail(String email){
		return this.memberDao.findByEmail(email);
	}
	@Transactional(readOnly = true)
	public List<Member> findListByEmail(String email) {
		return memberDao.findListByEmail(email);
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Member getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return memberDao.find(principal.getId());
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Override
	public void save(Member member, Admin operator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Member member, Integer modifyPoint,
			BigDecimal modifyBalance, String depositMemo, Admin operator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object[]> findPurchaseList(Date beginDate, Date endDate,
			Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

}