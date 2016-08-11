package com.bianmaren.dao.impl;

import javax.persistence.FlushModeType;

import com.bianmaren.dao.LogDao;
import com.bianmaren.entity.Log;

import org.springframework.stereotype.Repository;

/**
 * Dao - 日志
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}