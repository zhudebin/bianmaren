
package com.bianmaren.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.bianmaren.entity.BaseEntity;

public class EntityListener {

	@PrePersist
	public void prePersist(BaseEntity entity) {
		if(null == entity.getCreateDate()){
			entity.setCreateDate(new Date());
		}
		if(null == entity.getModifyDate()){
			entity.setModifyDate(new Date());
		}
	}

	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setModifyDate(new Date());
	}

}