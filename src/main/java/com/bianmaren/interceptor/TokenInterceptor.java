
package com.bianmaren.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bianmaren.util.WebUtils;

public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final String TOKEN_ATTRIBUTE_NAME = "token";

	private static final String TOKEN_COOKIE_NAME = "token";

	private static final String TOKEN_PARAMETER_NAME = "token";

	private static final String ERROR_MESSAGE = "Bad or missing token!";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = WebUtils.getCookie(request, TOKEN_COOKIE_NAME);
		if (request.getMethod().equalsIgnoreCase("POST")) {
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
					return true;
				} else {
					response.addHeader("tokenStatus", "accessDenied");
				}
			} else {
				if (token != null && token.equals(request.getParameter(TOKEN_PARAMETER_NAME))) {
					return true;
				}
			}
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token,null,"/","",null);
			}
//			response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
//			return false; //原始版本当中的
			return true;
		} else {
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token,null,"/","",null);
			}
			request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
			return true;
		}
	}

}