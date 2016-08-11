package com.bianmaren.controller.api;


/**
 * @author treebear
 *
 * @since 2015年11月7日下午4:37:54
 *
 * @description
 */
public class ApiResult<T> {

	private boolean success = true;
	private String resultCode;
	private String resultMsg;
	private T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
