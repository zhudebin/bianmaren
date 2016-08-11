package com.bianmaren.dao.impl;

import com.bianmaren.dao.RoleDao;
import com.bianmaren.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * Dao - 角色
 * 
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}