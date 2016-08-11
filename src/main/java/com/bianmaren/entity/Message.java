/**
 * @Description: 消息表实体类
 * @author 441889070@qq.com
 * @date 2015-11-03
 * @version V1.0
 */
package com.bianmaren.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "t_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_message_sequence")
public class Message extends BaseEntity {

	private static final long serialVersionUID = -5035343536762850722L;

	public enum MessageType{
		系统通知,
		订单通知,
		提现通知
	}
	
	/** 标题 */
	private String title;

	/** 内容 */
	private String content;

	/** ip */
	private String ip;

	/** 是否有正文 */
	private Boolean isHasContent;
	
	/** 是否为草稿 */
	private Boolean isDraft;

	/** 发件人已读 */
	private Boolean senderRead;

	/** 收件人已读 */
	private Boolean receiverRead;

	/** 发件人删除 */
	private Boolean senderDelete;

	/** 收件人删除 */
	private Boolean receiverDelete;

	/** 发件人 */
	private Member sender;

	/** 收件人 */
	private Member receiver;

	/** 原消息 */
	private Message forMessage;

	/** 回复消息 */
	private Set<Message> replyMessages = new HashSet<Message>();

	private MessageType messageTpe;
	
	public Message() {
		
	}
	
	public Message(MessageType type, Member receiver,String title,String content){
		this.messageTpe = type;
		this.receiver = receiver;
		this.title = title;
		this.content = content;
		this.senderRead = false;
		this.senderDelete = false;
		this.receiverDelete = false;
		this.receiverRead = false;
		this.isDraft = false;
		if(null == content){
			isHasContent = false;
		}
	}
	
	@JsonProperty
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty
	@Length(max = 1000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonProperty
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@JsonProperty
	public Boolean getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}

	@JsonProperty
	public Boolean getSenderRead() {
		return senderRead;
	}
	public void setSenderRead(Boolean senderRead) {
		this.senderRead = senderRead;
	}

	@JsonProperty
	public Boolean getReceiverRead() {
		return receiverRead;
	}
	public void setReceiverRead(Boolean receiverRead) {
		this.receiverRead = receiverRead;
	}

	@JsonProperty
	public Boolean getSenderDelete() {
		return senderDelete;
	}
	public void setSenderDelete(Boolean senderDelete) {
		this.senderDelete = senderDelete;
	}

	@JsonProperty
	public Boolean getReceiverDelete() {
		return receiverDelete;
	}
	public void setReceiverDelete(Boolean receiverDelete) {
		this.receiverDelete = receiverDelete;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Member getSender() {
		return sender;
	}
	public void setSender(Member sender) {
		this.sender = sender;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Member getReceiver() {
		return receiver;
	}
	public void setReceiver(Member receiver) {
		this.receiver = receiver;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Message getForMessage() {
		return forMessage;
	}
	public void setForMessage(Message forMessage) {
		this.forMessage = forMessage;
	}

	@OneToMany(mappedBy = "forMessage", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy(value = "createDate asc")
	public Set<Message> getReplyMessages() {
		return replyMessages;
	}
	public void setReplyMessages(Set<Message> replyMessages) {
		this.replyMessages = replyMessages;
	}

	
	@JsonProperty
	public MessageType getMessageTpe() {
		return messageTpe;
	}
	public void setMessageTpe(MessageType messageTpe) {
		this.messageTpe = messageTpe;
	}
	@JsonProperty
	public Boolean getIsHasContent() {
		return isHasContent;
	}
	public void setIsHasContent(Boolean isHasContent) {
		this.isHasContent = isHasContent;
	}

}