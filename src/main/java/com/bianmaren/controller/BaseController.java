package com.bianmaren.controller;

import com.bianmaren.DateEditor;
import com.bianmaren.Message;
import com.bianmaren.entity.Log;
import com.bianmaren.freemarker.template.directive.FlashMessageDirective;
import com.bianmaren.util.SpringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

public class BaseController {

	protected static final String ERROR_VIEW = "/admin/common/error";

	protected static final String FRONT_ERROR_VIEW = "/web/common/error";

	protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");

	protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");

	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	public static final String WX_APPID = "wx83e0eb486525edba";
	public static final String WX_APPSECRET = "9ac48d47d46829f351feed7e758ca287"; //应用密钥

	@Resource(name = "validator")
	private Validator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}


	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}

	protected void addLog(String content) {
		if (content != null) {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME, content, RequestAttributes.SCOPE_REQUEST);
		}
	}

}