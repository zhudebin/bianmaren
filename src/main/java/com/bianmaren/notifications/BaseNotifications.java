package com.bianmaren.notifications;

import java.util.Map;

/**
 * 系统通知的基类
 * @author dengwenbing
 *
 */
public abstract class BaseNotifications {

	/**
	 * 
	 * @Description: 获取系统通知
	 * @author 441889070@qq.com
	 * @date 2015年11月16日 下午5:16:26
	 * @param
	 * Map<String,String>
	 * 	count:数目
	 *  url:跳转链接
	 *  title:通知标题
	 */
	public abstract Map<String,Object> getNotifications();	
}
