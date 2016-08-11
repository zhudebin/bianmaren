package com.bianmaren.dao;

import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.entity.Member;
import com.bianmaren.entity.Message;

public interface MessageDao extends BaseDao<Message, Long> {

	Page<Message> findPage(Member member, Pageable pageable);

	Page<Message> findDraftPage(Member sender, Pageable pageable);

	Long count(Member member, Boolean read);

	void remove(Long id, Member member);

}