
package com.bianmaren.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.bianmaren.Filter;
import com.bianmaren.Order;
import com.bianmaren.dao.TagDao;
import com.bianmaren.entity.Tag;
import com.bianmaren.entity.Tag.Type;
import com.bianmaren.service.TagService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl<Tag, Long> implements TagService {

	@Resource(name = "tagDaoImpl")
	private TagDao tagDao;

	@Resource(name = "tagDaoImpl")
	public void setBaseDao(TagDao tagDao) {
		super.setBaseDao(tagDao);
	}

	@Transactional(readOnly = true)
	public List<Tag> findList(Type type) {
		return tagDao.findList(type);
	}

	@Transactional(readOnly = true)
	@Cacheable("tag")
	public List<Tag> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion) {
		return tagDao.findList(null, count, filters, orders);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void save(Tag tag) {
		super.save(tag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public Tag update(Tag tag) {
		return super.update(tag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public Tag update(Tag tag, String... ignoreProperties) {
		return super.update(tag, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Tag tag) {
		super.delete(tag);
	}


	/**
	 * 根据名字获取标签
	 * @param name
	 * @return
	 */
	public Tag getTagByName(String name){
		Tag tag = null;

		List<Filter> filters = new ArrayList<>();
		filters.add(Filter.eq("name",name));
		filters.add(Filter.eq("type",Tag.Type.article));

		List<Tag> tagList = findList(1,filters,null);
		if(null != tagList && tagList.size()>0){
			tag = tagList.get(0);
		}else{
			tag = new Tag();
			tag.setName(name);
			tag.setType(Tag.Type.article);
			save(tag);
		}
		return tag;
	}


}