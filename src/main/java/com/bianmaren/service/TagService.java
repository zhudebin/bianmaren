package com.bianmaren.service;

import java.util.List;

import com.bianmaren.Filter;
import com.bianmaren.Order;
import com.bianmaren.entity.Tag;
import com.bianmaren.entity.Tag.Type;

public interface TagService extends BaseService<Tag, Long> {

	List<Tag> findList(Type type);

	List<Tag> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

	Tag getTagByName(String name);
}