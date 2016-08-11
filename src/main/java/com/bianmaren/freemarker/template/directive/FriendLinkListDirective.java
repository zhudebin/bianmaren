package com.bianmaren.freemarker.template.directive;

import com.bianmaren.Filter;
import com.bianmaren.Order;
import com.bianmaren.entity.FriendLink;
import com.bianmaren.service.FriendLinkService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 模板指令 - 友情链接列表
 */
@Component("friendLinkListDirective")
public class FriendLinkListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "friendLinks";

	@Resource(name = "friendLinkServiceImpl")
	private FriendLinkService friendLinkService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<FriendLink> friendLinks;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		List<Filter> filters = getFilters(params, FriendLink.class);
		List<Order> orders = getOrders(params);
		if (useCache) {
			friendLinks = friendLinkService.findList(count, filters, orders, cacheRegion);
		} else {
			friendLinks = friendLinkService.findList(count, filters, orders);
		}
		setLocalVariable(VARIABLE_NAME, friendLinks, env, body);
	}

}