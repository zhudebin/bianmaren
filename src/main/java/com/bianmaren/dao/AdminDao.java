package com.bianmaren.dao;

import com.bianmaren.entity.Admin;


public interface AdminDao extends BaseDao<Admin, Long> {

	boolean usernameExists(String username);

	Admin findByUsername(String username);

}