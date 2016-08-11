package com.bianmaren.freemarker.template.directive;

import com.bianmaren.entity.Navigation;
import com.bianmaren.service.NavigationService;
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
 * 模板指令 - 获取所有导航
 */
@Component("navigationDirective")
public class NavigationDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "navigations";

	@Resource(name = "navigationServiceImpl")
	private NavigationService navigationervice;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Navigation> navigations = navigationervice.getAllEnableNavigation();
		setLocalVariable(VARIABLE_NAME, navigations, env, body);
	}



}