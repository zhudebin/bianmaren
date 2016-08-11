package com.bianmaren.service.impl;

import com.bianmaren.Page;
import com.bianmaren.Pageable;
import com.bianmaren.dao.MessageDao;
import com.bianmaren.entity.Member;
import com.bianmaren.entity.Message;
import com.bianmaren.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Long> implements MessageService {

	@Resource(name = "messageDaoImpl")
	private MessageDao messageDao;

	@Resource(name = "messageDaoImpl")
	public void setBaseDao(MessageDao messageDao) {
		super.setBaseDao(messageDao);
	}

	@Transactional(readOnly = true)
	public Page<Message> findPage(Member member, Pageable pageable) {
		return messageDao.findPage(member, pageable);
	}

	@Transactional(readOnly = true)
	public Page<Message> findDraftPage(Member sender, Pageable pageable) {
		return messageDao.findDraftPage(sender, pageable);
	}

	@Transactional(readOnly = true)
	public Long count(Member member, Boolean read) {
		return messageDao.count(member, read);
	}

	public void delete(Long id, Member member) {
		messageDao.remove(id, member);
	}


	@Override
	public void save(Message entity) {
		if (null == entity) return;

		Member member = entity.getReceiver();
		final String device_type = member.getDevice_type();
		final String device_tokens = member.getDevice_tokens();
		final String title = entity.getTitle();
		final String content = entity.getContent();

		super.save(entity);
	}
}