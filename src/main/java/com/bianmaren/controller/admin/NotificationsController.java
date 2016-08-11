package com.bianmaren.controller.admin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bianmaren.notifications.BaseNotifications;
import com.bianmaren.util.ClassUtils;
import com.bianmaren.util.JsonUtils;

@Controller("notificationsController")
@RequestMapping("/admin/notifications")
public class NotificationsController {
	
	/**
	 * 
	 * @Description: 获取所有通知
	 * @author 441889070@qq.com
	 * @date 2015年11月16日 下午5:00:26
	 * @param
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNotifications", method = RequestMethod.POST)
	@ResponseBody
	public String getNotifications() {
		
		Map<String,Object> mapReturn = new HashMap<String, Object>();
		List<Map<String,Object>> mapResult = new ArrayList<Map<String,Object>>();
		try {
			for (Class<?> cc : ClassUtils.getAllAssignedClass(BaseNotifications.class)) {
				 Object invokeTester = cc.getConstructor(new Class[]{}).newInstance(new Object[]{});
				 Method addMethod = cc.getMethod("getNotifications", new Class[]{});
				 Object result = addMethod.invoke(invokeTester, new Object[]{});
				 Map<String,Object> mapTmp = (Map<String,Object>)result;
				 if(mapTmp.containsKey("count") && (Long)mapTmp.get("count")>0){
					 mapResult.add((Map<String,Object>)result);
				 }
			}
			mapReturn.put("status", true);
			mapReturn.put("data", mapResult);
			
			return JsonUtils.getResultFromMap(mapReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mapReturn.put("status", false);
		return JsonUtils.getResultFromMap(mapReturn);
	}
}
