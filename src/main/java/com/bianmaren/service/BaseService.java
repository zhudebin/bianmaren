
package com.bianmaren.service;

import java.io.Serializable;
import java.util.List;

import com.bianmaren.Filter;
import com.bianmaren.Order;
import com.bianmaren.Page;
import com.bianmaren.Pageable;

public interface BaseService<T, ID extends Serializable> {

	T find(ID id);

	List<T> findAll();

	List<T> findList(ID... ids);

	List<T> findList(Integer count, List<Filter> filters, List<Order> orders);

	List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

	Page<T> findPage(Pageable pageable);
	Page<T> findPage(Pageable pageable,List<Filter> filters,List<Order> orders);

	long count();

	long count(Filter... filters);

	boolean exists(ID id);

	boolean exists(Filter... filters);

	void save(T entity);

	T update(T entity);

	T update(T entity, String... ignoreProperties);

	void delete(ID id);

	void delete(ID... ids);

	void delete(T entity);
	
}