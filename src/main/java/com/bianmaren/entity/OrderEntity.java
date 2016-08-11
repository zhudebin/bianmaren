/**
 * @Description: 排序基类
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@MappedSuperclass
public abstract class OrderEntity extends BaseEntity implements Comparable<OrderEntity> {

	private static final long serialVersionUID = 5995013015967525827L;

	/** "排序"属性名称 */
	public static final String ORDER_PROPERTY_NAME = "order";

	/** 排序 */
	private Integer order = 0;

	@JsonProperty
	@Field(store = Store.YES, index = Index.NO)
	@Min(0)
	@Column(name = "orders")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 实现compareTo方法
	 * 
	 * @param orderEntity 排序对象
	 * 
	 * @return 比较结果
	 */
	public int compareTo(OrderEntity orderEntity) {
		return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
	}

}