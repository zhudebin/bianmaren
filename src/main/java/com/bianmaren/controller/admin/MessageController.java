package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.Member;
import com.bianmaren.service.MemberService;
import com.bianmaren.service.MessageService;
import com.bianmaren.util.SpringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller - 消息
 * 
 */
@Controller("adminMessageController")
@RequestMapping("/admin/message")
public class MessageController extends BaseController {

	@Resource(name = "messageServiceImpl")
	MessageService messageService;
	@Resource(name = "memberServiceImpl")
	MemberService memberService;

	
	/**
	 * 
	 * @Description: 消息推送
	 * @author 441889070@qq.com
	 * @date 2015年11月30日 上午10:18:01
	 * @param
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("types",Message.Type.values());
		return "/admin/message/send";
	}
	
	/**
	 * 
	 * @Description: 发送动作
	 * @author 441889070@qq.com
	 * @date 2015年11月30日 上午10:23:33
	 * @param
	 */
	@RequestMapping(value = "/sendAction", method = RequestMethod.POST)
	@ResponseBody
	public Message send_action(ModelMap model,
			final String sendScope,final Boolean isHasContent,final String phone,final String title,final String content) {
		
		if("single".equals(sendScope)){
			Member member = memberService.findByMobile(phone);
			if(null == member){
				return Message.error("用户不存在");
			}
			com.bianmaren.entity.Message message = new com.bianmaren.entity.Message();
			message.setIsHasContent(null == isHasContent?false:isHasContent);
			message.setTitle(title);
			message.setReceiver(member);
			message.setMessageTpe(com.bianmaren.entity.Message.MessageType.系统通知);
			message.setContent(content);
			message.setSenderRead(false);
			message.setSenderDelete(false);
			message.setReceiverDelete(false);
			message.setReceiverRead(false);
			message.setIsDraft(false);
			messageService.save(message);
			return SUCCESS_MESSAGE;
			
		}else{
			ThreadPoolTaskExecutor taskExecutor = SpringUtils.getBean("taskExecutor", ThreadPoolTaskExecutor.class);
			taskExecutor.execute(new Thread(){
				@Override
				public void run() {
					List<Member> allUser = memberService.findAll();
					for(Member member:allUser){
						com.bianmaren.entity.Message message = new com.bianmaren.entity.Message();
						message.setIsHasContent(null == isHasContent?false:isHasContent);
						message.setTitle(title);
						message.setReceiver(member);
						message.setMessageTpe(com.bianmaren.entity.Message.MessageType.系统通知);
						message.setContent(content);
						message.setSenderRead(false);
						message.setSenderDelete(false);
						message.setReceiverDelete(false);
						message.setReceiverRead(false);
						message.setIsDraft(false);
						messageService.save(message);
					}
				}
			});
			
			return SUCCESS_MESSAGE;
		}
		
		
		
	}
	

}