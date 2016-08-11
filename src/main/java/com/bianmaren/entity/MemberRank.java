package com.bianmaren.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "t_member_rank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_member_rank_sequence")
public class MemberRank extends BaseEntity {

	private static final long serialVersionUID = 3599029355500655209L;

	/** 名称 */
	private String name;

	/** 优惠比例 */
	private Double scale;

	/** 消费金额 */
	private BigDecimal amount;


	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@JsonProperty
	@NotEmpty
	@Length(max = 100)
	@Column(nullable = false, unique = true, length = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取优惠比例
	 * 
	 * @return 优惠比例
	 */
	@JsonProperty
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	@Column(nullable = false, precision = 12, scale = 6)
	public Double getScale() {
		return scale;
	}

	/**
	 * 设置优惠比例
	 * 
	 * @param scale
	 *            优惠比例
	 */
	public void setScale(Double scale) {
		this.scale = scale;
	}

	/**
	 * 获取消费金额
	 * 
	 * @return 消费金额
	 */
	@JsonProperty
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(unique = true, precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置消费金额
	 * 
	 * @param amount
	 *            消费金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		
	}

}