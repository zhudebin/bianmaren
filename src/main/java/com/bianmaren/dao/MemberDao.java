package com.bianmaren.dao;

import java.util.List;

import com.bianmaren.entity.Member;

public interface MemberDao extends BaseDao<Member, Long> {

	boolean usernameExists(String username);

	public boolean mobileExists(String mobile);
	
	boolean emailExists(String email);

	Member findByUsername(String username);
	
	Member findByMobile(String mobile);

	Member findByEmail(String email);

	List<Member> findListByEmail(String email);

}