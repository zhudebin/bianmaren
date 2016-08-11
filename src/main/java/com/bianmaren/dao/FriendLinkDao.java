package com.bianmaren.dao;

import java.util.List;

import com.bianmaren.entity.FriendLink;
import com.bianmaren.entity.FriendLink.Type;

public interface FriendLinkDao extends BaseDao<FriendLink, Long> {

	/**
	 * 查找友情链接
	 * 
	 * @param type  类型
	 * 
	 * @return 友情链接
	 */
	List<FriendLink> findList(Type type);

}