package com.bianmaren.dao;

import java.util.List;

import com.bianmaren.entity.Tag;
import com.bianmaren.entity.Tag.Type;

public interface TagDao extends BaseDao<Tag, Long> {

	List<Tag> findList(Type type);

}