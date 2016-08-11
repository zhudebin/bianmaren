package com.bianmaren.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * 
 * @ClassName: ShiroTagFreeMarkerConfigurer 
 * @Description: 让freemarker中可以适应 shiro标签
 * @author dengwenbing  
 * @date 2015年6月23日 下午4:41:41 
 *
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{

	 @Override
     public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
     }
	 
}
