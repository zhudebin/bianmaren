package com.bianmaren.dao.impl;

import java.math.BigDecimal;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.bianmaren.dao.MemberRankDao;
import com.bianmaren.entity.MemberRank;

@Repository("memberRankDaoImpl")
public class MemberRankDaoImpl extends BaseDaoImpl<MemberRank, Long> implements MemberRankDao {

	public boolean nameExists(String name) {
		if (name == null) {
			return false;
		}
		String jpql = "select count(*) from MemberRank memberRank where lower(memberRank.name) = lower(:name)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
		return count > 0;
	}

	public boolean amountExists(BigDecimal amount) {
		if (amount == null) {
			return false;
		}
		String jpql = "select count(*) from MemberRank memberRank where memberRank.amount = :amount";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).getSingleResult();
		return count > 0;
	}

	public MemberRank findByAmount(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		String jpql = "select memberRank from MemberRank memberRank where memberRank.amount <= :amount order by memberRank.amount desc";
		return entityManager.createQuery(jpql, MemberRank.class).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).setMaxResults(1).getSingleResult();
	}

	/**
	 * 处理默认并保存
	 * 
	 * @param memberRank
	 *            会员等级
	 */
	@Override
	public void persist(MemberRank memberRank) {
		Assert.notNull(memberRank);
		super.persist(memberRank);
	}

	/**
	 * 处理默认并更新
	 * 
	 * @param memberRank
	 *            会员等级
	 * @return 会员等级
	 */
	@Override
	public MemberRank merge(MemberRank memberRank) {
		Assert.notNull(memberRank);
		return super.merge(memberRank);
	}

	/**
	 * 忽略默认、清除会员价并删除
	 * 
	 * @param memberRank 会员等级
	 * 
	 */
	@Override
	public void remove(MemberRank memberRank) {
		if (memberRank != null) {
			super.remove(super.merge(memberRank));
		}
	}

}