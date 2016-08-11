package com.bianmaren.freemarker.template.directive;

import com.bianmaren.entity.CarouselImage;
import com.bianmaren.service.CarouselImageService;
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
 * 模板指令 - 轮播图列表
 */
@Component("carouselImageListDirective")
public class CarouselImageListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "carouselImages";

	@Resource(name = "carouselImageServiceImpl")
	private CarouselImageService carouselImageService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<CarouselImage> carouselImages = carouselImageService.getAllEnableCarouselImage();
		setLocalVariable(VARIABLE_NAME, carouselImages, env, body);
	}

}