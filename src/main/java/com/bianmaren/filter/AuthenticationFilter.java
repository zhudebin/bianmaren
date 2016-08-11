
package com.bianmaren.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bianmaren.shiro.AuthenticationToken;
import com.bianmaren.service.RSAService;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class AuthenticationFilter extends FormAuthenticationFilter {

	private static final String DEFAULT_EN_PASSWORD_PARAM = "enPassword";

	private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";

	private static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String enPasswordParam = DEFAULT_EN_PASSWORD_PARAM;

	private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	/**
	 * 创建 Token
	 */
	@Override
	protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
	    String username = getUsername(servletRequest);//用户名
		String password = getPassword(servletRequest);//密码
		String captchaId = getCaptchaId(servletRequest);
		String captcha = getCaptcha(servletRequest);//验证码
		boolean rememberMe = isRememberMe(servletRequest);//是否记住密码
		String host = getHost(servletRequest);//IP
		return new AuthenticationToken(username, password, captchaId, captcha, rememberMe, host);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "accessDenied");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		return super.onAccessDenied(request, response);
	}

	/**
	 * 认证
	 */
	@Override
	protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token, Subject subject, ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		Session session = subject.getSession();
		Map<Object, Object> attributes = new HashMap<Object, Object>();
		Collection<Object> keys = session.getAttributeKeys();
		for (Object key : keys) {
			attributes.put(key, session.getAttribute(key));
		}
		session.stop();
		session = subject.getSession();
		for (Entry<Object, Object> entry : attributes.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}
		return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
	}
	
	
	/**
	 * 获取用户输入密码
	 */
	@Override
	protected String getPassword(ServletRequest servletRequest) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String password = rsaService.decryptParameter(enPasswordParam, request);
		rsaService.removePrivateKey(request);
		return password;
	}

	
	protected String getCaptchaId(ServletRequest servletRequest) {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		String captchaId = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captchaId == null) {
			captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
		}
		return captchaId;
	}
	
	/**
	 * 获取验证码
	 * @param servletRequest
	 * @return
	 */
	protected String getCaptcha(ServletRequest servletRequest) {
		return WebUtils.getCleanParam(servletRequest, captchaParam);
	}

	public String getEnPasswordParam() {
		return enPasswordParam;
	}

	public void setEnPasswordParam(String enPasswordParam) {
		this.enPasswordParam = enPasswordParam;
	}

	public String getCaptchaIdParam() {
		return captchaIdParam;
	}

	public void setCaptchaIdParam(String captchaIdParam) {
		this.captchaIdParam = captchaIdParam;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

}