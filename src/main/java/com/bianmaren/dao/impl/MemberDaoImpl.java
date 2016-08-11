
package com.bianmaren.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.bianmaren.dao.MemberDao;
import com.bianmaren.entity.Member;

@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}
	
	public boolean mobileExists(String mobile) {
		if (mobile == null) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.mobile) = lower(:mobile)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("mobile", mobile).getSingleResult();
		return count > 0;
	}

	public boolean emailExists(String email) {
		if (email == null) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.email) = lower(:email)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
		return count > 0;
	}

	public Member findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select members from Member members where lower(members.username) = lower(:username)";
			return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Member findByEmail(String email){
		if (email == null) {
			return null;
		}
		try {
			String jpql = "select members from Member members where lower(members.email) = lower(:email)";
			return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Member findByMobile(String mobile){
		if (mobile == null) {
			return null;
		}
		try {
			String jpql = "select members from Member members where lower(members.mobile) = lower(:mobile)";
			return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("mobile", mobile).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Member> findListByEmail(String email) {
		if (email == null) {
			return Collections.<Member> emptyList();
		}
		String jpql = "select members from Member members where lower(members.email) = lower(:email)";
		return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getResultList();
	}

	

}