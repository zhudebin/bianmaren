package com.bianmaren.service;

import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.entity.Member;
import com.bianmaren.entity.Message;

public interface MessageService extends BaseService<Message, Long> {

	Page<Message> findPage(Member member, Pageable pageable);

	Page<Message> findDraftPage(Member sender, Pageable pageable);

	Long count(Member member, Boolean read);

	void delete(Long id, Member member);

}