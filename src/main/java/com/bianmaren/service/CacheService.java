package com.bianmaren.service;

public interface CacheService {

	String getDiskStorePath();

	int getCacheSize();

	void clear();

	void clearSetting();
}