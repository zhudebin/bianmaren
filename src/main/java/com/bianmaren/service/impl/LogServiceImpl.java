package com.bianmaren.service.impl;

import javax.annotation.Resource;

import com.bianmaren.dao.LogDao;
import com.bianmaren.entity.Log;
import com.bianmaren.service.LogService;

import org.springframework.stereotype.Service;

/**
 * Service - 日志
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

	public void clear() {
		logDao.removeAll();
	}

}