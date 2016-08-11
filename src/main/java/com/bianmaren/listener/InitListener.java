/**
 * 系统初始化监听器，当系统执行初始化的时候启动
 */
package com.bianmaren.listener;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.bianmaren.service.CacheService;
import com.bianmaren.service.StaticService;

@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {


	private static final Logger logger = Logger.getLogger(InitListener.class.getName());

	private ServletContext servletContext;

	@Value("${system.version}")
	private String systemVersion;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
	    if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {

			//将最新的setting值放入freemarker变量
			cacheService.clearSetting();

			//主页进行静态化
			staticService.buildIndex();
		}
	}

}